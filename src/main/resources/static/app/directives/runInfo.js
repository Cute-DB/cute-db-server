(function(){

    var app = angular.module('cute-db-directives', []);

    app.directive('runInfo', function(){
        return{
            restrict: 'E',
            templateUrl: '../../templates/directives/run-info.html'
        };
    });

})();