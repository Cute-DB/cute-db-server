(function(angular) {
    var HATEOAS_URL = './runs';
    var RunFactory = function($http, SpringDataRestAdapter) {
        function Run(run) {

            if (run._resources) {
                run.resources = run._resources("self", {}, {
                    update: {
                        method: 'PUT'
                    }
                });
                run.save = function (callback) {
                    run.resources.update(run, function () {
                        callback && callback(run);
                    });
                };

                run.remove = function (callback) {
                    run.resources.remove(function () {
                        callback && callback(run);
                    });
                };
            } else {
                run.save = function (callback) {
                    Run.resources.save(run, function (run, headers) {
                        var deferred = $http.get(headers().location);
                        return SpringDataRestAdapter.process(deferred).then(function (newRun) {
                            callback && callback(new Run(newRun));
                        });
                    });
                };
            }

            return run;
        }

        Run.query = function (callback) {
            var deferred = $http.get(HATEOAS_URL);
            return SpringDataRestAdapter.process(deferred).then(function (data) {
                Run.resources = data._resources("self");
                callback && callback(_.map(data._embeddedItems, function (run) {
                    return new Run(run);
                }));
            });
        };

        Run.resources = null;
        return Run;
    };

    RunFactory.$inject = ['$http', 'SpringDataRestAdapter'];
    angular.module("myApp.services").factory("Run", RunFactory);

}(angular));