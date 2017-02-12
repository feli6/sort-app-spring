angular.module('sortApp', [])
    .controller('SortNumbersController', function($scope, $http) {
        var self = this;
        $scope.submit = submit;
        $scope.sortNumbers = sortNumbers;
        $scope.getAllSortResults = getAllSortResults;

        self.successMessage = '';
        self.errorMessage = '';

        function submit() {
            console.log('Submitting');
            sortNumbers($scope.numbersToSort)
        }


        function sortNumbers(numbersToSort) {
            console.log('About to sort numbers');

            $http.post('/sortNumbers', numbersToSort)
                .then(
                    function (response) {
                        console.log('Numbers sorted successfully');
                        $scope.successMessage = 'Numbers sorted successfully';
                        $scope.errorMessage='';
                        $scope.sortResult=response.data;
                        getAllSortResults();
                    },
                    function (errResponse) {
                        console.error('Error while sorting numbers');
                        $scope.errorMessage = 'Error while sorting numbers: ' + errResponse.data.message;
                        $scope.successMessage='';
                    }
                );
        }

        function getAllSortResults(){
            $http.get('/sortNumbers')
                .then(
                    function (response) {
                        console.log('Fetched successfully all sort results');
                        $scope.sortResultList = response.data;
                    },
                    function (errResponse) {
                        console.error('Error while loading sort results');
                        $scope.errorMessage = 'Error while loading sort results';
                    }
                );
        }
    });
