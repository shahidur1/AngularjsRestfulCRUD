var app = angular.module("simpleApp", []);

app.controller("simpleCtrl", function ($scope, $http) {


    $scope.simples = [];
    $scope.simpleForm = {
        id: -1,
        firstName: "",
        lastName: ""
    };

    _refreshSimpleData();

    $scope.submitSimple = function () {

        var method = "";
        var url = "";
        if ($scope.simpleForm.id == -1) {
            method = "POST";
            url = 'service/simple';
        } else {

            method = "PUT";
            url = 'service/simple/up/' + $scope.simpleForm.id;
        }

        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.simpleForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    //HTTP DELETE- delete Message by Id
    $scope.deleteSimple = function (simple) {
        $http({
            method: 'DELETE',
            url: 'service/simple/del/' + simple.id
        }).then(_success, _error);
    };

    $scope.editSimple = function (simple) {
        $scope.simpleForm.id = simple.id,
                $scope.simpleForm.firstName = simple.firstName,
                $scope.simpleForm.lastName = simple.lastName
    };

    function _refreshSimpleData() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/SimpleAngularjsCRUD/service/simple'
        }).then(function successCallback(response) {
            $scope.simples = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    function _success(response) {
        _refreshSimpleData();
        _clearFormData()
    }

    function _error(response) {
        console.log(response.statusText);
    }


    function _clearFormData() {
        $scope.simpleForm.id = -1,
        $scope.simpleForm.firstName = "",
        $scope.simpleForm.lastName = ""
    }
    ;

});