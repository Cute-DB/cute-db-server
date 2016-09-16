(function(){

    var app = angular.module('cute-db-directives', []);

    app.directive('lintInfo', function(){
        return{
            restrict: 'E',
            templateUrl: '../../templates/directives/lint-info.html'
        };
    });

})();