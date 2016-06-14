(function(angular) {
    var AppController = function($scope, Run) {
        Run.query(function(response) {
            $scope.runs = response ? response : [];
        });

        $scope.addRun = function(jdbcUrl) {
            new Run({
                jdbcUrl: jdbcUrl,
                status: 'PENDING',
                checked: false
            }).save(function(run) {
                $scope.runs.push(run);
            });
            $scope.newRun = "";
        };

        $scope.updateRun = function(run) {
            run.save();
        };

        $scope.deleteRun = function(run) {
            run.remove(function() {
                $scope.runs.splice($scope.runs.indexOf(run), 1);
            });
        };
    };

    AppController.$inject = ['$scope', 'Run'];
    angular.module("myApp.controllers").controller("AppController", AppController);
}(angular));