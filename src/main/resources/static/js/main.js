var app = angular.module("myApp", ["ngRoute"]);

app.config(function ($routeProvider, $httpProvider) {
  // var auth = "Basic " + btoa("user:123");
  $httpProvider.defaults.headers.common["Authorization"] =
    "Basic YWRtaW42OjY2Ng==";
});

let host = "http://localhost:8080/rest";

app.controller("CartController", function ($scope, $http) {
  // var url = `${host}/products/${id}`;

  $scope.ids = [];
  $scope.product = {};
  $scope.totalPrice = 0;

  $scope.checkIfProductExists = (obj) => {
    return $scope.ids.some((item) => angular.equals(item, obj));
  };

  $scope.add = (id) => {
    $http
      .get(`/rest/products/${id}`)
      .then((resp) => {
        $scope.product = resp.data;

        if ($scope.checkIfProductExists($scope.product)) {
          return;
        }

        $scope.ids.push($scope.product);

        console.log("Success", resp);
      })
      .catch((error) => {
        console.log("Error", error);
      });

    var message = "Product added to cart successfully!";
    $scope.saveToLocalStorage();
    $scope.showAlert(message);
  };

  $scope.total = () => {
    return $scope.ids
      .map((item) => item.unitPrice * item.quantity)
      .reduce((totalPrice, quantity) => (totalPrice += quantity), 0);
  };

  $scope.showAlert = (message) => {
    var alertBox = document.createElement("div");
    alertBox.innerHTML = message;
    alertBox.style.cssText =
      "position:fixed;top:13.5%;right:13.5%;width:20%;border-radius: 10px;background-color:rgba(242, 113, 37,0.7);color:white;text-align:center;padding:20px;z-index:9999;";
    document.body.appendChild(alertBox);

    setTimeout(function () {
      document.body.removeChild(alertBox);
    }, 1500);
  };

  $scope.saveToLocalStorage = () => {
    var json = JSON.stringify(angular.copy($scope.ids));
    localStorage.setItem("cart", json);
  };

  $scope.loadFromLocalStorage = () => {
    var json = localStorage.getItem("cart");
    $scope.ids = json ? JSON.parse(json) : [];
  };

  $scope.loadFromLocalStorage();
});
