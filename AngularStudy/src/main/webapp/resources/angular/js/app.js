 var app = angular.module("app", ["ngRoute"])
        .constant("indredientUrl", "/ingredient")
        .constant("countryUrl", "/country")
        .constant("msUrl", "/ms")
        .constant("amountUrl", "/amount")
        .constant("recipeUrl", "/recipe");
        app.config(function ($routeProvider, $locationProvider) {
            $routeProvider.when("/admin/ingredient", {
                templateUrl: "/resources/views/ingredient.html"
            });
            $routeProvider.when("/admin/ms", {
                templateUrl: "/resources/views/ms.html"
            });
            $routeProvider.when("/admin/country", {
                templateUrl: "/resources/views/country.html"
            });
            $routeProvider.when("/admin/amount", {
                templateUrl: "/resources/views/amount.html"
            });
            $routeProvider.when("/admin/recipe", {
                templateUrl: "/resources/views/recipe.html"
            });
            $routeProvider.otherwise({
                templateUrl: "/resources/views/main.html"
            });
            $locationProvider.html5Mode(true);
        });
        app.controller("navController", function($scope){
            $scope.links = [{class:""},
                           {class:""},
                           {class:""},
                           {class:""},
                           {class:""}]
            $scope.clickActivate = function(index){
                for(var i in $scope.links) {
                    $scope.links[i].class="";
                }
                $scope.links[index].class="active";
            };
        });