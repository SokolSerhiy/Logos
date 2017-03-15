<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
<c:if test="${param.fail}">
	<div class="col-sm-12 col-xs-12 text-center">
		Fail to authorize
	</div>
</c:if>
	<div class="col-sm-12 col-xs-12">
		<form:form class="form-horizontal" action="/login" method="POST">
			<div class="form-group">
    			<label for="login" class="col-sm-2 control-label">Login</label>
    			<div class="col-sm-10">
      				<input class="form-control" name="login" id="login">
    			</div>
  			</div>
			<div class="form-group">
    			<label for="password" class="col-sm-2 control-label">Password</label>
    			<div class="col-sm-10">
      				<input class="form-control" name="password" id="password">
    			</div>
  			</div>
  			<div class="form-group">
  				<div class="col-sm-offset-2 col-sm-10">
  					<div class="checkbox">
  						<label>
  							<input name="remember-me" type="checkbox"> Remember me
  						</label>
  					</div>
  				</div>
  			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      				<button type="submit" class="btn btn-default">Sign in</button>
    			</div>
  			</div>
		</form:form>
	</div>
</div>