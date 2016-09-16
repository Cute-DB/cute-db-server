(function() {
    angular.module('toggleDirective', [])
        .directive('toggle', function () {
            return {
                restrict: 'A',
                link: function (scope, element, attrs) {
                    if (attrs.toggle == "tooltip") {
                        $(element).tooltip();
                    }
                }
            };
        });
})();