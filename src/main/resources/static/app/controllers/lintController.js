(function() {
    angular.module('cuteDB')
        .controller('runController', ['$scope', '$location', 'Run', function($scope, $location, Run) {

            var feed = new EventSource('/runs/register');
            var handler = function(event){
                var newRun = JSON.parse(event.data);
                $scope.$apply(function () {
                   $scope.runs.push(newRun);
                });

            }
            feed.addEventListener('newRun', handler, false);

            Run.query(function(response) {
                $scope.runs = response ? response : [];
            });

            $scope.getRun = function(uuid){
                $scope.selectedRun = run.$getRun(uuid);
            }

            $scope.addRun = function(jdbcUrl) {
                new Run({
                    jdbcUrl: jdbcUrl,
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

            $scope.showDetail = function(run){
                $location.path('/runs/'+ run.uuid);
            };
        }]);


})();