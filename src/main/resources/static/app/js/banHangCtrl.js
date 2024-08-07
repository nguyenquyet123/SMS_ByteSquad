app.controller("banHangCtrl", function ($scope, $http) {
  $scope.currentPage = 1;
  $scope.itemsPerPage = 8;
  $scope.totalItems;

  $scope.setPage = (pageNo) => {
    $scope.currentPage = pageNo;
    $scope.pageChanged();
    $scope.message = "";
  };

  $scope.pageChanged = () => {
    var begin = ($scope.currentPage - 1) * $scope.itemsPerPage;
    var end = begin + $scope.itemsPerPage;
    if ($scope.totalItems % 2 !== 0) {
      if ($scope.totalItems < end) {
        var begin = ($scope.currentPage - 1) * $scope.itemsPerPage - 1;
      }
      if ($scope.totalItems === end) {
        $scope.currentPage += 0.5;
      }
    }
    $scope.page = $scope.productWithImage.slice(begin, end);
  };

  $scope.load_all = () => {
    var url = `${host}/products`;
    var productImagesUrl = `${host}/product-images`;
    Promise.all([$http.get(url), $http.get(productImagesUrl)])
      .then(([productsResp, imagesResp]) => {
        $scope.products = productsResp.data;
        $scope.productImages = imagesResp.data;

        $scope.productWithImage = $scope.products.map((product) => {
          const images = $scope.productImages
            .filter((image) => image.product.productId === product.productId)
            .map((image) => image.url);
          return {
            ...product,
            images,
          };
        });
        $scope.totalItems = $scope.productWithImage.length;
        $scope.pageChanged();
        console.log("pageChanged", $scope.page);
        console.log("Success", $scope.products);
      })
      .catch((error) => {
        console.log("Error", error);
      });
    $scope.test = [
      {
        id: "1",
        testName: "a",
      },
      {
        id: "2",
        testName: "b",
      },
    ];
  };

  $scope.load_all();
  // Quan Ly Gio hang
  $scope.cart = {
    items: [],
    //Them san pham
    add(id) {
      var item = this.items.find((item) => item.productId == id);
      if (item) {
        item.qty++;
        this.saveToLocalStorage();
      } else {
        $http.get(`/api/products/${id}`).then((resp) => {
          resp.data.qty = 1;
          this.items.push(resp.data);
          this.saveToLocalStorage();
        });
      }
      this.showAlert("Thêm mới Thành Công");
    },
    showAlert(mess) {
      var alertBox = document.createElement("div");
      alertBox.innerHTML = mess;
      alertBox.style.cssText =
        "position:fixed;top:9%;right:40%;width:20%;border-radius: 10px;background-color:rgba(100, 147, 255,1);color:white;text-align:center;padding:20px;z-index:9999;";
      document.body.appendChild(alertBox);

      setTimeout(function () {
        document.body.removeChild(alertBox);
      }, 1500); // Đóng alert sau 1 giây
    },
    remove(id) {
      var index = this.items.findIndex((item) => item.productId == id);
      this.items.splice(index, 1);
      this.saveToLocalStorage();
      this.showAlert("Xóa Thành Công");
    },
    clear() {
      this.items = [];
      this.saveToLocalStorage();
    },
    get count() {
      return this.items
        .map((item) => item.qty)
        .reduce((total, qty) => (total += qty), 0);
    },
    get amount() {
      return this.items
        .map((item) => item.qty * item.unitPrice)
        .reduce((total, qty) => (total += qty), 0);
    },
    //Luu gio hang vao local storage
    saveToLocalStorage() {
      var json = JSON.stringify(angular.copy(this.items));
      localStorage.setItem("cart_app", json);
    },
    //Doc gio hang tu local storage
    loadFromLocalStorage() {
      var json = localStorage.getItem("cart_app");
      this.items = json ? JSON.parse(json) : [];
    },
  };

  $scope.cart.loadFromLocalStorage();

  $scope.order = {
    orderType: "off",
    orderDate: new Date(),
    get totalPrice() {
      return $scope.cart.amount;
    },
    orderStatus: 1,
    branch: { branchId: 3 },
    get orderDetails() {
      return $scope.cart.items.map((item) => {
        return {
          product: { productId: item.productId },
          price: item.unitPrice,
          quantity: item.quantity,
        };
      });
    },
    purchase() {
      var order = angular.copy($scope.order);
      $http
        .post(`${host}/orders`, order)
        .then((resp) => {
          alert("Đặt Hàng thành công");
          $scope.cart.clear();
          // location.href = "http://localhost:8080/sms/orderhistory";
        })
        .catch((error) => {
          alert("Đặt Hàng thất bại");
          console.log(error);
        });
    },
  };
});
