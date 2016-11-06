(function() {
    angular.module('cuteDB')
        .controller('runShowController',['$http', '$routeParams', '$scope', '$location', function($http, $routeParams, $scope, $location) {


            $scope.tabs = [
                {
                    "heading": "Dashboard",
                    "active": true,
                    "template":"/templates/pages/runs/dashboard.html"
                },
                {
                    "heading": "Details",
                    "active": false,
                    "template":"/templates/pages/lints/index.html"
                },

            ];
            $scope.tabs.activeTab = 0;

            $http({method:'GET', url:'/runs/uuid/'+$routeParams.uuid})
                .success(function (data) {
                    $scope.selectedRun = data;
                    if($scope.selectedRun.ended) {

                        var delta = Math.abs($scope.selectedRun.ended - $scope.selectedRun.started) / 1000;
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

                        $scope.elapsedTime = '';
                        if(hours != null && hours != 0)
                            $scope.elapsedTime = hours + ' h ';
                        if(minutes != null && minutes != 0)
                            $scope.elapsedTime = $scope.elapsedTime + minutes + ' min ';
                        if(seconds != null && seconds != 0)
                            $scope.elapsedTime = $scope.elapsedTime + seconds + ' sec';
                    }

                    var now = new Date();
                    $scope.launched = '';
                    var delta = Math.abs($scope.selectedRun.started - now) / 1000;
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

                    $scope.launched = '';
                    if(hours != null && hours != 0)
                        $scope.launched = hours + ' h ';
                    if(minutes != null && minutes != 0)
                        $scope.launched = $scope.launched + minutes + ' min ';
                    if(seconds != null && seconds != 0)
                        $scope.launched = $scope.launched + seconds + ' sec';


                    $scope.donutLabels = ["Critical", "High", "Medium", "Low"];
                    $scope.donutData = [$scope.selectedRun.criticalHits,$scope.selectedRun.highHits, $scope.selectedRun.mediumHits, $scope.selectedRun.lowHits];

                    /*var colorArray = ['#4572A7', '#71588F', '#DB843D', '#AA4643'];
                     $scope.colorFunction = function() {
                     return function(d, i) {
                     return colorArray[i];
                     };
                     }


                     $scope.donutOptions = {
                     chart: {
                     type: 'pieChart',
                     height: 450,
                     donut: true,
                     x: function(d){return d.key;},
                     y: function(d){return d.y;},
                     showLabels: true,
                     color:$scope.colorFunction(),
                     }
                     };


                     $scope.donutData = [
                     {
                     key: "Low",
                     y: $scope.selectedRun.lowHits
                     },
                     {
                     key: "Medium",
                     y: $scope.selectedRun.mediumHits
                     },
                     {
                     key: "High",
                     y: $scope.selectedRun.highHits
                     },
                     {
                     key: "Critical",
                     y: $scope.selectedRun.criticalHits
                     }
                     ];*/

                });

            $http({method:'GET', url:'/runs/uuid/'+$routeParams.uuid+'/lints'})
                .success(function (data) {

                    // Get run lints
                    var lints = data ? data : [];
                    var sortedLints = {};

                    for (var i = 0, len = lints.length; i < len; i++) {

                        if (!sortedLints[lints[i].objectName]) {
                            var severityCount = {critical: 0, high: 0, medium: 0, low: 0};
                            sortedLints[lints[i].objectName] = severityCount;
                        }
                        if (lints[i].severity == 'critical') {
                            sortedLints[lints[i].objectName].critical++;
                        }
                        if (lints[i].severity == 'high') {
                            sortedLints[lints[i].objectName].high++;
                        }
                        if (lints[i].severity == 'medium') {
                            sortedLints[lints[i].objectName].medium++;
                        }
                        if (lints[i].severity == 'high') {
                            sortedLints[lints[i].objectName].low++;
                        }
                    }




                    $scope.barChartSeries = ["Critical", "High", "Medium", "Low"];
                    $scope.barChartOption = {
                        type:'StackedBar',
                        scales: {
                            xAxes: [{
                                stacked: true,
                            }],
                            yAxes: [{
                                stacked: true
                            }]
                        }
                    };


                    $scope.barChartLabels = [];
                    var criticalCounts = [];
                    var highCounts = [];
                    var mediumCounts = [];
                    var lowCounts = [];
                    var count;

                    for (var i = 0, len = Object.keys(sortedLints).length; i < len; i++) {

                        count = Object.values(sortedLints)[i];
                        $scope.barChartLabels.push(Object.keys(sortedLints)[i]);

                        criticalCounts.push(count.critical);
                        highCounts.push(count.high);
                        mediumCounts.push(count.medium);
                        lowCounts.push(count.low);

                    }

                    $scope.barChartData = [criticalCounts, highCounts, mediumCounts, lowCounts ];

                });

            // Get run lints
            $scope.showLints = function(uuid){
                $location.path('/runs/' + uuid + '/lints');
                console.log('/runs/' + uuid + '/lints')
            };

        }]);
})();