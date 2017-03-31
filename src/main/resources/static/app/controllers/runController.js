(function() {
    angular.module('cuteDB')
        .controller('runController', ['$scope', '$location', 'Run', function($scope, $location, Run) {

            var connect = function () {
                var feed = new EventSource('http://localhost:9000/runs/register');

                // Handle correct opening of connection
                feed.addEventListener('open', function (e) {
                    console.log('Connected.');
                });

                // Update the run list
                feed.addEventListener('message', function (e) {
                    var run = JSON.parse(e.data);
                    var found = false;
                    for(var i=0;i< $scope.runs.length;i++){
                        if($scope.runs[i].uuid === run.uuid){
                            if(run.status === 'ABORTED'){
                               // console.log("logs---->");
                                //$("#runs").children().splice(i,1);
                                delete $scope.runs[i];
                            }
                            else {
                                $scope.runs[i] = run
                            }
                            found = true;
                            break;
                        }
                    }
                    // }
                    //console.log("run: " + run.uuid);
                    if(!found) {
                        $scope.runs.unshift(run);
                    }
                    $scope.$apply();
                }, false);

                // Reconnect if the connection fails
                feed.addEventListener('error', function (e) {
                    console.log('Disconnected.');
                    if (e.readyState == EventSource.CLOSED) {
                        connected = false;
                        connect();
                    }
                }, false);


            }



            connect();

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


            $scope.getRunElapsedTime = function (run){

                if (run.ended) {

                    var delta = Math.abs(run.ended - run.started) / 1000;
                    // calculate (and subtract) whole days
                    var days = Math.floor(delta / 86400);
                    delta -= days * 86400;
                    // calculate (and subtract) whole hours
                    var hours = Math.floor(delta / 3600) % 24;
                    delta -= hours * 3600;
                    // calculate (and subtract) whole minutes
                    var minutes = Math.floor(delta / 60) % 60;
                    delta -= minutes * 60;
                    // what's left is seconds
                    var seconds = delta % 60;  // in theory the modulus is not required

                    var elapsedTime = '';
                    if (hours != null && hours != 0)
                        elapsedTime = hours + ' h ';
                    if (minutes != null && minutes != 0)
                        elapsedTime = elapsedTime + minutes + ' min ';
                    if (seconds != null && seconds != 0)
                        elapsedTime = elapsedTime + seconds.toString().substring(0,4) + ' sec';

                }
                return elapsedTime;
            };

            $scope.getRunLaunchedTime = function (run){
                var now = new Date();

                var delta = Math.abs(run.started - now) / 1000;
                // calculate (and subtract) whole days
                var days = Math.floor(delta / 86400);
                delta -= days * 86400;
                // calculate (and subtract) whole hours
                var hours = Math.floor(delta / 3600) % 24;
                delta -= hours * 3600;
                // calculate (and subtract) whole minutes
                var minutes = Math.floor(delta / 60) % 60;
                delta -= minutes * 60;
                // what's left is seconds
                var seconds = Math.round(delta % 60);  // in theory the modulus is not required

                var launched = '';
                if (hours != null && hours != 0)
                    launched = hours + ' h ';
                if (minutes != null && minutes != 0)
                    launched = launched + minutes + ' min ';
                if (seconds != null && seconds != 0)
                    launched = launched + seconds + ' sec';

                return launched;
            }

        }]);


})();