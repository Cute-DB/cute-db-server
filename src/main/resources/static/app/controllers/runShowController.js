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
                    "heading": "Dashboard 2",
                    "active": true,
                    "template":"/templates/pages/runs/dashboard2.html"
                },
                {
                    "heading": "Details",
                    "active": false,
                    "template":"/templates/pages/lints/index.html"
                }

            ];
            $scope.tabs.activeTab = 0;

            $http({method:'GET', url:'/runs/uuid/'+$routeParams.uuid})
                .success(function (data) {
                    $scope.selectedRun = data;

                    if(!$scope.selectedRun){
                        $location.path('/404');
                    }
                    else {
                        if ($scope.selectedRun.ended) {

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
                            if (hours != null && hours != 0)
                                $scope.elapsedTime = hours + ' h ';
                            if (minutes != null && minutes != 0)
                                $scope.elapsedTime = $scope.elapsedTime + minutes + ' min ';
                            if (seconds != null && seconds != 0)
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
                        if (hours != null && hours != 0)
                            $scope.launched = hours + ' h ';
                        if (minutes != null && minutes != 0)
                            $scope.launched = $scope.launched + minutes + ' min ';
                        if (seconds != null && seconds != 0)
                            $scope.launched = $scope.launched + seconds + ' sec';


                        $scope.donutLabels = ['Critical', 'High', 'Medium', 'Low'];
                        $scope.donutData = [$scope.selectedRun.criticalHits, $scope.selectedRun.highHits, $scope.selectedRun.mediumHits, $scope.selectedRun.lowHits];
                        $scope.donutColors = $scope.barChartColors = ['#F40707', '#F78181', '#FDB45C', '#7BD66D'];
                        $scope.donutOptions = {
                            legend: {display: true},
                            scaleShowLabels: true
                        };

                        $scope.badgeBuild = {name: 'build', value: $scope.selectedRun.status};
                        switch ($scope.selectedRun.status) {
                            case 'SUCCESS':
                                $scope.badgeBuild.color = 'green';
                                break;
                            case 'FAILURE':
                                $scope.badgeBuild.color = 'red';
                                break;
                            case 'RUNNING':
                                $scope.badgeBuild.color = 'orange';
                                break;
                            case 'PENDING':
                                $scope.badgeBuild.color = 'blue';
                                break;
                        }

                        $scope.badgeScore = {name: 'score', value: $scope.selectedRun.weightedScore};
                        if ($scope.selectedRun.weightedScore < 25) {
                            $scope.badgeScore.color = 'red';
                        } else if ($scope.selectedRun.weightedScore < 50) {
                            $scope.badgeScore.color = 'orange';
                        } else if ($scope.selectedRun.weightedScore < 75) {
                            $scope.badgeScore.color = 'yellow';
                        } else {
                            $scope.badgeScore.color = 'green';
                        }

                        $scope.badgeStars = {name: 'rating'};
                        switch ($scope.selectedRun.score) {
                            case 0:
                                $scope.badgeStars.value = '☆☆☆☆☆';
                                break;
                            case 1:
                                $scope.badgeStars.value = '★☆☆☆☆';
                                break;
                            case 2:
                                $scope.badgeStars.value = '★★☆☆☆';
                                break;
                            case 3:
                                $scope.badgeStars.value = '★★★☆☆';
                                break;
                            case 4:
                                $scope.badgeStars.value = '★★★★☆';
                                break;
                            case 5:
                                $scope.badgeStars.value = '★★★★★';
                                break;
                        }

                        if ($scope.selectedRun.score < 1) {
                            $scope.badgeStars.color = 'red';
                        } else if ($scope.selectedRun.score <= 2) {
                            $scope.badgeStars.color = 'orange';
                        } else if ($scope.selectedRun.score <= 4) {
                            $scope.badgeStars.color = 'yellow';
                        } else {
                            $scope.badgeStars.color = 'green';
                        }
                    }
                });


            $http({method:'GET', url:'/runs/uuid/'+$routeParams.uuid+'/lints/top/5'})
                .success(function (data) {

                    // Get run lints
                    var lints = data ? data : [];
                    var sortedLints = {};

                    for (var i = 0, len = lints.length; i < len; i++) {

                        if (!sortedLints[lints[i].objectName]) {
                            var severityCount = {name : lints[i].objectName , critical: 0, high: 0, medium: 0, low: 0};
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
                        if (lints[i].severity == 'low') {
                            sortedLints[lints[i].objectName].low++;
                        }
                    }

                    $scope.barChartSeries = ['Critical', 'High', 'Medium', 'Low'];
                    $scope.barChartOption = {
                        legend: {display: true},
                        type:'StackedBar',
                        scaleShowLabels : true,
                        scales: {
                            xAxes: [{
                                stacked: true,
                            }],
                            yAxes: [{
                                stacked: true,
                            }]
                        }
                    };
                    $scope.barChartColors =  [ '#F40707', '#F78181', '#FDB45C', '#7BD66D']

                    $scope.barChartLabels = [];
                    var criticalCounts = [];
                    var highCounts = [];
                    var mediumCounts = [];
                    var lowCounts = [];
                    var count;

                    for (var i = 0, len =  Object.keys(sortedLints).length; i < len; i++) {

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