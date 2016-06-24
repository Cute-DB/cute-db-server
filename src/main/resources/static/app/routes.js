angular.module('cuteDB')
    .config(function($routeProvider){
        $routeProvider.when('/runList', {
            templateUrl:'/templates/pages/runs/index.html',
        })

        .otherwise({redirectTo:'/'});
    });