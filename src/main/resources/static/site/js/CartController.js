app.controller("CartController", function ($scope, $http) {
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

          $scope.productWithImgs = $scope.productWithImgs.map((item) =>
            item.product.productId === existingProduct.product.productId
              ? existingProduct
              : item
          );
        } else {
          $scope.product.quantity = 1;
          $scope.products.push($scope.product);

          $http.get(`${host}/product-images`).then((resp) => {
            $scope.productImages = resp.data;

            let productImage = $scope.productImages.find(
              (img) => img.product.productId === $scope.product.productId
            );

            $scope.productWithImgs.push({
              product: $scope.product,
              imgUrl: productImage ? productImage.url : null,
            });
          });
        }
      })
      .catch((error) => {
        console.log("Error", error);
      });
    $scope.saveToLocalStorage();
    var message = "Product added to cart successfully!";
    $scope.showAlert(message);
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

  $scope.loadFromLocalStorage();

// ----------Xu ly dat hang

  $scope.order = {};

  $scope.placeOrder = () => {
    $http.push(`${host}/orders`).then((resp) =>{
      
    });
  };

});
