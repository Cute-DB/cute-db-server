angular.module('cuteDB')
    .config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider){
        $routeProvider.when('/runs', {
            templateUrl:'/templates/pages/runs/index.html',
            controller: 'runController'
            })

            .when('/runs/:uuid', {
                templateUrl:'/templates/pages/runs/show.html',
                controller: 'runShowController'
            })

            // .when('/runs/:uuid/#current', {
            //     templateUrl:'/templates/pages/runs/show.html',
            //     controller: 'runShowController'
            // })
            // .when('/runs/:uuid/#details', {
            //     templateUrl:'/templates/pages/runs/show.html',
            //     controller: 'runShowController'
            // })

            .when('/runs/:uuid/lints', {
                templateUrl:'/templates/pages/lints/index.html',
                controller: 'runDetailController'
            })
            .when('/404', {templateUrl : '/templates/404.html'})

            .otherwise({redirectTo:'/runs'});

            // $locationProvider.html5Mode({
            //     enabled: true,
            //     requireBase: false
            // });
    }]);