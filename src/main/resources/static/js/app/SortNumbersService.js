'use strict';

angular.module('sortApp').factory('SortNumbersService',
    ['$http', '$q',
        function ( $http, $q) {

            var factory = {
                loadAllSortResults: loadAllSortResults,
                sortNumbers: sortNumbers
            };

            return factory;

            function loadAllSortResults() {
                console.log('Fetching all sort results');
                var deferred = $q.defer();
                $http.get('/sortNumbers')
                    .then(
                        function (response) {
                            console.log('Fetched successfully all sort results');
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading sort results');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function sortNumbers(numbersToSort) {
                console.log('Sorting numbers');
                var deferred = $q.defer();
                $http.post('/sortNumbers', numbersToSort)
                    .then(
                        function (response) {
                            //loadAllSortResults();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while sorting numbers : '+errResponse.data.errorMessage);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);