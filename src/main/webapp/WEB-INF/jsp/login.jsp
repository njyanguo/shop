<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN" >
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>商城</title>
	<jsp:include page="common/common.jsp"></jsp:include>
  </head>

  <body>

    <div class="container">

      <form class="form-signin">
        <h2 class="form-signin-heading" style="text-align: center;">登   录</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" id="userId" class="form-control" placeholder="用户名" ng-bind="userId" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
       <input type="password" id="password" class="form-control"  ng-bind="password" placeholder="密码" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me">记住我
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登  录</button>
      </form>

    </div>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../js/bootstrap/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>