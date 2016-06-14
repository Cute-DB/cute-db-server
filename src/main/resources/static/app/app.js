(function(angular) {
    angular.module("myApp.controllers", []);
    angular.module("myApp.services", []);
    angular.module("myApp", ["ngResource", "spring-data-rest", "myApp.controllers", "myApp.services"])
        .config(function (SpringDataRestInterceptorProvider) {
            SpringDataRestInterceptorProvider.apply();
        });
}(angular));