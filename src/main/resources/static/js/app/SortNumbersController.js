'use strict';

angular.module('sortApp').controller('SortNumbersController',
    ['SortNumbersService', '$scope',  function( SortNumbersService, $scope) {

        var self = this;

        self.submit = submit;
        self.sortNumbers = sortNumbers;
        self.getAllSortResults = getAllSortResults;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;

        function submit() {
            console.log('Submitting');
            sortNumbers(self.numbersToSort)
        }


        function sortNumbers(numbersToSort) {
            console.log('About to sort numbers');
            SortNumbersService.sortNumbers(numbersToSort)
                .then(
                    function (response) {
                        console.log('Numbers sorted successfully');
                        self.successMessage = 'Numbers sorted successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.sortResult=response.data;
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while sorting numbers');
                        self.errorMessage = 'Error while sorting numbers: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }

        function getAllSortResults(){
            return SortNumbersService.getAllSortResults();
        }

    }
    ]);