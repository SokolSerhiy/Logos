<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
  <style>
            .navbar-brand{
                padding: 0 15px;
            }
  </style>
  <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#one" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="/" class="navbar-brand">
                        <img src="/resources/img/brang.jpg" class="img img-thumbnail" width="56px">
                    </a>
                </div>
                <div class="collapse navbar-collapse" id="one">
                    <ul class="nav navbar-nav">
                        <li><a href="">Link 1</a></li>
                        <li class="dropdown">
                            <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                                Dropdown <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="">Menu 1</a></li>
                                <li><a href="">Menu 2</a></li>
                                <li><a href="">Menu 3</a></li>
                                <li><a href="">Menu 4</a></li>
                            </ul>
                        </li>
                    </ul>
                    <security:authorize access="!isAuthenticated()">
	                    <form:form class="navbar-form navbar-right" action="/login" method="POST">
	                        <div class="form-group">
	                            <input class="form-control" placeholder="Login" name="login">
	                        </div>
	                        <div class="form-group">
	                            <input class="form-control" placeholder="Password" type="password" name="password">
	                        </div>
	                        <div class="checkbox">
							    <label>
							      <input name="remember-me" type="checkbox"> Remember me
							    </label>
							</div>
	                        <button class="btn btn-primary">Sign in</button>
	                    </form:form>
                    </security:authorize>
                    <security:authorize access="isAuthenticated()">
	                    <form:form class="navbar-form navbar-right" action="/logout" method="POST">
	                        <button class="btn btn-primary">Sign out</button>
	                    </form:form>
                    	<security:authorize access="hasRole('ROLE_ADMIN')">
		                    <ul class="nav navbar-nav navbar-right">
		                        <li><a href="/admin">Admin</a></li>
		                        <li class="dropdown">
		                            <a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		                                Entities <span class="caret"></span>
		                            </a>
		                            <ul class="dropdown-menu">
		                                <li><a href="/admin/category">Category</a></li>
		                                <li><a href="/admin/ms">Measuring system</a></li>
										<li><a href="/admin/producer">Producer</a></li>
										<li><a href="/admin/nosd">Name of specification digital</a></li>
										<li><a href="/admin/noss">Name of specification string</a></li>
										<li><a href="/admin/ss">Specification string</a></li>
										<li><a href="/admin/item">Item</a></li>
		                            </ul>
		                        </li>
		                    </ul>
                    	</security:authorize>
                    </security:authorize>
                </div>
            </div>
        </nav>