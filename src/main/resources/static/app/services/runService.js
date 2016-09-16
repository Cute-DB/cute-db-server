(function() {
    angular.module('cuteDB')
        .factory('Run', ['$resource', function RunFactory($resource){
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
    }]);

})();