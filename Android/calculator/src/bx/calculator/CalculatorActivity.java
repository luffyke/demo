package bx.calculator;

import bx.util.DoubleValueArith;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

public class CalculatorActivity extends Activity {
    
    private TextView textView = null;
    
    private double inputValueDouble = 0.0;
    
    private int inputValue = 0;

    private String inputValueStr = null;

    private StringBuffer inputValueStringBuffer = null;
    
    private int lastValue = 0;
    
    private double lastValueDouble = 0.0;
    
    private int currentValue = 0;
    
    private double currentValueDouble = 0.0;

    private boolean isLastOperationNumbers = false;
    
    private char operation;
    
    private static final String POINT = ".";
    
    private static final char PLUS = '+';
    
    private static final char MINUS = '-';
    
    private static final char DIV = '/';
    
    private static final char MULTIPLY = '*';
    
    private static final char REMAINDER = '%';
    
    private int clickEqualTimes = 0;
    
    private boolean isDouble;
    
    private boolean isError;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TableLayout calculatorTableLayout = (TableLayout) findViewById(R.id.calculatorTableLayout);
        calculatorTableLayout.setStretchAllColumns(true);

        textView = (TextView) findViewById(R.id.inputValue);
        inputValueStr = textView.getText().toString();
        inputValueStringBuffer = new StringBuffer(inputValueStr);

        setNumberButtonsOnClickListener();
        setOperationButtonsOnClickListener();
    }

    private void setNumberButtonsOnClickListener() {
        
        // Number 0
        ImageButton zeroButton = (ImageButton) findViewById(R.id.zeroButton);
        zeroButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (isError) {
                    return;
                }
                if ((inputValue != 0 || inputValueStr.length() > 1) && isLastOperationNumbers) {
                    inputValueStr = inputValueStringBuffer.append(String.valueOf(0)).toString();
                    textView.setText(inputValueStr);
                } else {
                    inputValue = 0;
                    inputValueDouble = 0.0;
                    currentValue = 0;
                    currentValueDouble = 0;
                    inputValueStr = "0";
                    inputValueStringBuffer = new StringBuffer(inputValueStr);
                    textView.setText(inputValueStr);
                }
                isLastOperationNumbers = true;
            }
        });

        // Number 1
        ImageButton oneButton = (ImageButton) findViewById(R.id.oneButton);
        oneButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                setNumbers(1);
            }
        });
        
        // Number 2
        ImageButton twoButton = (ImageButton) findViewById(R.id.twoButton);
        twoButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                setNumbers(2);
            }
        });
        
        // Number 3
        ImageButton threeButton = (ImageButton) findViewById(R.id.threeButton);
        threeButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                setNumbers(3);
            }
        });
        
        // Number 4
        ImageButton fourButton = (ImageButton) findViewById(R.id.fourButton);
        fourButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                setNumbers(4);
            }
        });
        
        // Number 5
        ImageButton fiveButton = (ImageButton) findViewById(R.id.fiveButton);
        fiveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                setNumbers(5);
            }
        });
        
        // Number 6
        ImageButton sixButton = (ImageButton) findViewById(R.id.sixButton);
        sixButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                setNumbers(6);
            }
        });
        
        // Number 7
        ImageButton sevenButton = (ImageButton) findViewById(R.id.sevenButton);
        sevenButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                setNumbers(7);
            }
        });
        
        // Number 8
        ImageButton eightButton = (ImageButton) findViewById(R.id.eightButton);
        eightButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                setNumbers(8);
            }
        });
        
        // Number 9
        ImageButton nineButton = (ImageButton) findViewById(R.id.nineButton);
        nineButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                setNumbers(9);
            }
        });

        // Button Point
        ImageButton pointButton = (ImageButton) findViewById(R.id.pointButton);
        pointButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (isError) {
                    return;
                }
                if (isLastOperationNumbers) {
                    if (inputValueStr.indexOf(POINT) == -1) {
                        inputValueStr = inputValueStringBuffer.append(POINT).toString();
                        textView.setText(inputValueStr);
                        isDouble = true;
                    }
                } else {
                    isLastOperationNumbers = true;
                    inputValueStr = "0.";
                    inputValueStringBuffer = new StringBuffer(inputValueStr);
                    isDouble = true;
                    textView.setText(inputValueStr);
                }
            }
        });
    }

    private void setOperationButtonsOnClickListener() {
        // Clear Operation
        ImageButton clearButton = (ImageButton) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // Clear numbers
                inputValueDouble = 0.0;
                inputValue = 0;
                inputValueStr = "0";
                inputValueStringBuffer = new StringBuffer(inputValueStr);
                currentValue = 0;
                currentValueDouble = 0.0;
                lastValue = 0;
                lastValueDouble = 0.0;
                isDouble = false;
                textView.setText(inputValueStr);
                
                // Clear operations
                operation = ' ';
                clickEqualTimes = 0;
                isLastOperationNumbers = false;
                isError = false;
            }

        });
        
        // Positive/Negative Operation
        ImageButton positiveOrNegativeButton = (ImageButton) findViewById(R.id.positiveOrNegativeButton);
        positiveOrNegativeButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                inputValueStr = textView.getText().toString();
                if (inputValueStr.indexOf("-") == -1) {
                    inputValueStringBuffer.insert(0, MINUS);
                } else {
                    inputValueStringBuffer.deleteCharAt(0);
                }
                inputValueStr = inputValueStringBuffer.toString();
                textView.setText(inputValueStr);
                
                if (inputValueStr.indexOf(POINT) == -1) {
                    inputValue = Integer.parseInt(inputValueStr);
                    inputValueDouble = inputValue;
                } else {
                    inputValueDouble = Double.parseDouble(inputValueStr);
                    isDouble = true;
                }
                // for next operation
                lastValue = inputValue;
                lastValueDouble = inputValueDouble;
            }

        });
        
        // Plus Operation
        ImageButton plusButton = (ImageButton) findViewById(R.id.plusButton);
        plusButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if (!isError) {
                    operation = PLUS;
                    setValuesForOperation();
                }
            }

        });
        
        // Minus Operation
        ImageButton minusButton = (ImageButton) findViewById(R.id.minusButton);
        minusButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if (!isError) {
                    operation = MINUS;
                    setValuesForOperation();
                }
            }

        });
        
        // Multiply Operation
        ImageButton multiplyButton = (ImageButton) findViewById(R.id.multiplyButton);
        multiplyButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if (!isError) {
                    operation = MULTIPLY;
                    setValuesForOperation();
                }
            }
        });
        
        // Div Operation
        ImageButton divButton = (ImageButton) findViewById(R.id.divButton);
        divButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if (!isError) {
                    operation = DIV;
                    setValuesForOperation();
                }
            }

        });
        
        // Remainder Operation
        ImageButton remainderButton = (ImageButton) findViewById(R.id.remainderButton);
        remainderButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if (!isError) {
                    operation = REMAINDER;
                    setValuesForOperation();
                }
            }

        });
        
        // Equal Operation
        ImageButton equalButton = (ImageButton) findViewById(R.id.equalButton);
        equalButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if (isError) {
                    return;
                }
                isLastOperationNumbers = false;
                clickEqualTimes++;
                int result = 0;
                double resultDouble = 0.0;
                inputValueStr = textView.getText().toString();
                if (operation == DIV || operation == REMAINDER) {
                    inputValueDouble = Double.parseDouble(inputValueStr);
                }
                if (inputValueStr.indexOf(POINT) != -1 || isDouble) {
                    inputValueDouble = Double.parseDouble(inputValueStr);
                    isDouble = true;
                } else {
                    inputValue = Integer.parseInt(inputValueStr);
                }
                switch (operation) {
                    case PLUS:
                        if (clickEqualTimes >= 2) {
                            if (isDouble) {
                                resultDouble = DoubleValueArith.add(inputValueDouble, currentValueDouble);
                            } else {
                                result = inputValue + currentValue;
                            }
                        } else {
                            if (isDouble) {
                                resultDouble = DoubleValueArith.add(inputValueDouble, lastValueDouble);
                            } else {
                                result = inputValue + lastValue;
                            }
                        }
                        break;
                    case MINUS:
                        if (clickEqualTimes >= 2) {
                            if (isDouble) {
                                resultDouble = DoubleValueArith.subtract(inputValueDouble, currentValueDouble);
                            } else {
                                result = inputValue - currentValue;
                            }
                        } else {
                            if (isDouble) {
                                resultDouble = DoubleValueArith.subtract(lastValueDouble, inputValueDouble);
                            } else {
                                result = lastValue - inputValue;
                            }
                        }
                        break;
                    case MULTIPLY:
                        if (clickEqualTimes >= 2) {
                            if (isDouble) {
                                resultDouble = DoubleValueArith.multiply(inputValueDouble, currentValueDouble);
                            } else {
                                result = inputValue * currentValue;
                            }
                        } else {
                            if (isDouble) {
                                resultDouble = DoubleValueArith.multiply(lastValueDouble, inputValueDouble);
                            } else {
                                result = lastValue * inputValue;
                            }
                        }
                        break;
                    case DIV:
                        if ((isDouble && inputValueDouble == 0) || (!isDouble && inputValue == 0)) {
                            isError = true;
                        }
                        if (!isError) {
                            if (clickEqualTimes >= 2) {
                                resultDouble = DoubleValueArith.divide(inputValueDouble, currentValueDouble);
                            } else {
                                resultDouble = DoubleValueArith.divide(lastValueDouble, inputValueDouble);
                            }
                        } else {
                            inputValueStringBuffer = new StringBuffer("除数不能为0");
                        }
                        break;
                    case REMAINDER:
                        if ((isDouble && inputValueDouble == 0) || (!isDouble && inputValue == 0)) {
                            isError = true;
                        }
                        if (!isError) {
                            if (clickEqualTimes >= 2) {
                                resultDouble = DoubleValueArith.remainder(inputValueDouble, currentValueDouble);
                            } else {
                                resultDouble = DoubleValueArith.remainder(lastValueDouble, inputValueDouble);
                            }
                        } else {
                            inputValueStringBuffer = new StringBuffer("除数不能为0");
                        }
                        break;
                }
                if (!isError) {
                    if (result == 0) {
                        if (DoubleValueArith.remainder(resultDouble, 1) == 0) {
                            inputValueStringBuffer = new StringBuffer(String.valueOf((int) resultDouble));
                        } else {
                            inputValueStringBuffer = new StringBuffer(String.valueOf(resultDouble));
                        }
                    } else {
                        inputValueStringBuffer = new StringBuffer(String.valueOf(result));
                    }
                }
                textView.setText(inputValueStringBuffer.toString());
            }

        });
    }

    private void setNumbers(int number) {
        if (isError) {
            return;
        }
        // zero or last operation is not numbers
        if ((inputValueStr != null && inputValueStr.length() == 1 && inputValue == 0) || !isLastOperationNumbers) {
            inputValue = number;
            inputValueStr = String.valueOf(inputValue);
            
            // reset buffer
            inputValueStringBuffer.delete(0, inputValueStringBuffer.length());
            inputValueStringBuffer.append(inputValueStr);
        } else {
            // append number
            inputValueStr = inputValueStringBuffer.append(String.valueOf(number)).toString();
        }
        // get currentValue for equal operation when click times >= 2
        if (inputValueStr.indexOf(POINT) == -1) {
            currentValueDouble = number;
            currentValue = number;
        } else {
            currentValueDouble = Double.parseDouble(inputValueStr);
        }
        isLastOperationNumbers = true;
        textView.setText(inputValueStr);
    }
    
    private void setValuesForOperation() {
        clickEqualTimes = 0;
        isLastOperationNumbers = false;
        
        inputValueStr = textView.getText().toString();
        if (inputValueStr.indexOf(POINT) == -1) {
            inputValue = Integer.parseInt(inputValueStr);
            inputValueDouble = inputValue;
        } else {
            inputValueDouble = Double.parseDouble(inputValueStr);
            isDouble = true;
        }
        // for equal operation
        lastValue = inputValue;
        lastValueDouble = inputValueDouble;
    }
}