@echo off
:: My redmine root path is E:\workspace\eclipse\redmine-2.1.2
E:
cd E:\workspace\eclipse\redmine-2.1.2
ruby script/rails server webrick -e production
cmd