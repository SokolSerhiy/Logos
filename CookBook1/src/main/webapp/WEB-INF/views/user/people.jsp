<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <sec:csrfMetaTags/>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.3/angular.min.js"></script>
    <script>
            var app = angular.module('app',[]);
            app.controller('mainContoller', function($scope, $http){
                $scope.people = [];
                $scope.currentItem = {};
                $scope.currentView = 'data';
                $scope.add = function(){
                    $scope.currentView = 'form';
                }
                $scope.cancel = function(){
                    $scope.currentView = 'data';
                    $scope.currentItem = {};
                }
                $scope.save = function(){
                	$http({
                		method:"PUT",
						url:"/people",
						data:$scope.currentItem,
						headers:{'X-CSRF-TOKEN':$("meta[name='_csrf']").attr("content")}
                	}).then(function(result) {
                		for(var i = 0; i < $scope.people.length; i++){
                			if(result.data.id==$scope.people[i].id){
                				$scope.people.splice(i, 1);
                			}
                		}
                		$scope.people.push(result.data);
					})
                    $scope.cancel();
                }
                $scope.update = function(item){
                    $scope.currentItem = item;
                    $scope.add();
                }
                $scope.delete = function(item){
                	$http({
                		method:"DELETE",
						url:"/people/"+item.id,
						headers:{'X-CSRF-TOKEN':$("meta[name='_csrf']").attr("content")}
                	}).then(function() {
                		for(var i = 0; i < $scope.people.length; i++){
                			if(item.id==$scope.people[i].id){
                				$scope.people.splice(i, 1);
                			}
                		}
                	});
                }
                $scope.refresh = function() {
					$http({
						method:"GET",
						url:"/people"
					}).then(function(result) {
						$scope.people = result.data;
					});
				}
                $scope.refresh();
            });
        </script>
<div ng-app="app" ng-controller="mainContoller">
            <div class="row" ng-show="currentView == 'data'">
                <div class="col-sm-3"></div>
                <div class="col-sm-9">
                    <div class="row">
                        <div class="col-sm-3">
                            <h2>Name</h2>
                        </div>
                        <div class="col-sm-3">
                            <h2>Age</h2>
                        </div>
                        <div class="col-sm-3">
                            <h2>Address</h2>
                        </div>
                        <div class="col-sm-3">
                            <h2>Options</h2>
                        </div>
                    </div>
                    <div class="row" ng-repeat="item in people">
                        <div class="col-sm-3">{{item.name}}</div>
                        <div class="col-sm-3">{{item.age}}</div>
                        <div class="col-sm-3">{{item.address}}</div>
                        <div class="col-sm-3">
                            <button class="btn btn-warning" ng-click="update(item)">Update</button>
                            <button class="btn btn-danger" ng-click="delete(item)">Delete</button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <button class="btn btn-primary" ng-click="add()">Add</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" ng-show="currentView == 'form'">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2">Name</label>
                        <div class="col-sm-10">
                            <input ng-model="currentItem.name" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">Age</label>
                        <div class="col-sm-10">
                            <input ng-model="currentItem.age" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">Address</label>
                        <div class="col-sm-10">
                            <input ng-model="currentItem.address" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10 col-sm-offset-2">
                            <button type="button" class="btn btn-success" ng-click="save()">Create</button>
                            <button type="button" class="btn btn-warning" ng-click="cancel()">Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>