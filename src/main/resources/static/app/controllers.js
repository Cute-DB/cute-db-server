(function(angular) {
    var AppController = function($scope, Run) {
        Run.query(function(response) {
            $scope.runs = response ? response : [];
        });

        $scope.addRun = function(description) {
            new Run({
                description: description,
                checked: false
            }).$save(function(run) {
                $scope.runs.push(run);
            });
            $scope.newRun = "";
        };

        $scope.updateRun = function(run) {
            run.$updateRun();
        };

        $scope.deleteRun = function(run) {
            run.$remove(function() {
                $scope.runs.splice($scope.runs.indexOf(run), 1);
            });
        };
    };

    AppController.$inject = ['$scope', 'Run'];
    angular.module("myApp.controllers").controller("AppController", AppController);
}(angular));