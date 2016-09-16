(function() {
    angular.module('cuteDB')
        .controller('lintController', ['$scope', '$location', 'Lint', function($scope, $location, Lint) {

            Lint.query(function(response) {
                $scope.lints = response ? response : [];
            });

            $scope.getLint = function(uuid){
                $scope.selectedLint = run.$getLint(uuid);
            }

            $scope.showDetail = function(lint){
                $location.path('/lints/'+ lint.runuuid);
            };
        }]);


})();