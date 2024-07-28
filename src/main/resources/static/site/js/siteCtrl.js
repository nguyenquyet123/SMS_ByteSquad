var site = angular.module("mySite", ["ngRoute"]);

site.config(function ($httpProvider) {
  $httpProvider.defaults.headers.common["Authorization"] = "Basic YWRtaW46MTIz";
});
let host = "http://localhost:8080/api";

site.controller("siteCtrl", function ($scope, $http) {
  // ------------Xu ly Cart

  $scope.products = [];
  $scope.product = {};

  $scope.productImages = [];
  $scope.productWithImgs = [];
  $scope.totalPrice = 0;

  $scope.loadFromLocalStorage = () => {
    var json = localStorage.getItem("cart");
    $scope.productWithImgs = json ? JSON.parse(json) : [];
  };

  $scope.saveToLocalStorage = () => {
    var json = JSON.stringify(angular.copy($scope.productWithImgs));
    localStorage.setItem("cart", json);
  };

  $scope.add = (id) => {
    $http
      .get(`${host}/products/${id}`)
      .then((resp) => {
        $scope.product = resp.data;

        let existingProduct = $scope.productWithImgs.find(
          (item) => item.product.productId === $scope.product.productId
        );

        if (existingProduct) {
          existingProduct.product.quantity += 1;
          $scope.saveToLocalStorage();
          var message = "Product added to cart successfully!";
          $scope.showAlert(message);
        } else {
          $scope.product.quantity = 1;
          $scope.products.push($scope.product);

          $http
            .get(`${host}/product-images`)
            .then((resp) => {
              $scope.productImages = resp.data;

              let productImage = $scope.productImages.find(
                (img) => img.product.productId === $scope.product.productId
              );

              $scope.productWithImgs.push({
                product: $scope.product,
                imgUrl: productImage ? productImage.url : null,
              });

              $scope.saveToLocalStorage();
              var message = "Product added to cart successfully!";
              $scope.showAlert(message);
            })
            .catch((error) => {
              console.error("Error fetching product images:", error);
            });
        }
      })
      .catch((error) => {
        console.error("Error fetching product:", error);
      });
  };

  $scope.remove = function (id) {
    $scope.productWithImgs = $scope.productWithImgs.filter(
      (item) => item.product.productId !== id
    );
    $scope.saveToLocalStorage();
  };

  $scope.total = () => {
    return $scope.productWithImgs
      .map((item) => item.product.unitPrice * item.product.quantity)
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
    }, 1000);
  };

  $scope.clear = () => {
    $scope.productWithImgs = [];
    $scope.saveToLocalStorage();
  };

  $scope.loadFromLocalStorage();

  // ------------Xu ly Place order

  $scope.order = {
    orderType: "order web",
    orderDate: new Date(),
    totalPrice: $scope.total(),
    orderStatus: 1,
    branch: { branchId: 3 },
    get orderDetails() {
      return $scope.productWithImgs.map((item) => {
        return {
          product: { productId: item.product.productId },
          price: item.product.unitPrice,
          quantity: item.product.quantity,
        };
      });
    },
    purchase() {
      var order = angular.copy($scope.order);
      $http
        .post(`${host}/orders`, order)
        .then((resp) => {
          alert("Đặt Hàng thành công");
          $scope.clear();
          // location.href = "http://localhost:8080/sms/orderhistory";
        })
        .catch((error) => {
          alert("Đặt Hàng thất bại");
          console.log(error);
        });
    },
  };

  // ------------Xu ly Order history

  // $scope.orderHistory = [];
  // $http
  //   .get(`${host}/orders`)
  //   .then((resp) => {
  //     $scope.orderHistory = resp.data;
  //     var username = { username: $("#username").text().trim() };
  //     $scope.filteredOrderHistory = $scope.orderHistory.filter(
  //       (order) => order.branch.managerBranch.username === username
  //     );
  //     console.log($scope.orderHistory);
  //   })
  //   .catch((error) => {
  //     console.log(error);
  //   });
});
