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

            //Get all runs
            Run.query(function(response) {
                $scope.runs = response ? response : [];
                $scope.runs.sort(function(a,b) {
                    return new Date(b.started) - new Date(a.started);
                })
            });

            // Get run by uuid
            $scope.getRun = function(uuid){
                $scope.selectedRun = run.$getRun(uuid);
            }

            // Create new run
            $scope.addRun = function(jdbcUrl) {
                new Run({
                    jdbcUrl: jdbcUrl,
                    checked: false
                }).$save(function(run) {
                    $scope.runs.push(run);
                });
                $scope.newRun = "";
            };

            // Update run
            $scope.updateRun = function(run) {
                run.$updateRun();
            };

            // Delete run
            $scope.deleteRun = function(run) {
                run.$remove(function() {
                    $scope.runs.splice($scope.runs.indexOf(run), 1);
                });
            };


            $scope.showInfo = function(run){
                $location.path('/runs/'+ run.uuid);
            };



        }]);


})();