angular.module('cuteDB')
    .config(['$routeProvider', function($routeProvider){
        $routeProvider.when('/runs', {
            templateUrl:'/templates/pages/runs/index.html',
            controller: 'runController'
        })

            .when('/runs/:uuid', {
                templateUrl:'/templates/pages/runs/show.html',
                controller: 'runShowController'
            })


            .otherwise({redirectTo:'/'});
    }]);