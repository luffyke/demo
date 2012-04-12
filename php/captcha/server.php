<?php
session_start();
if($_POST["vercode"] == $_SESSION["vercode"]){
	echo "<script>alert('Yeah')</script>";
} else {
	echo "<script>alert('Sorry')</script>";
}