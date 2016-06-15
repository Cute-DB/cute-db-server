(function(angular) {
    var RunFactory = function($resource) {
        return $resource('/runs/:id', {
            id: '@id'
        }, {
            update: {
                method: "PUT"
            },
            remove: {
                method: "DELETE"
            }
        });
    };

    RunFactory.$inject = ['$resource'];
    angular.module("myApp.services").factory("Run", RunFactory);
}(angular));