(function() {
    angular.module('cuteDB')
        .factory('Lint', ['$resource', function LintFactory($resource){
            return $resource('/lints/:id', {
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