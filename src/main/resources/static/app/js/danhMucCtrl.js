app.controller("danhMucCtrl", function ($scope, $http) {
    $scope.form ={};
    //Update
    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put(`/api/products/${item.id}`, item).then(reps => {
            var index = $scope.products.findIndex(p => p.productId == item.productId);
            $scope.products[index] = item;
            alert("Cap nhat thanh cong");
        }).catch(error => {
            alert("Cap nhat that bai");
            console.log("Error", error);
        })
    }
    //Hiển thị table
    $scope.selectedRow = null;
    $scope.showDetails = function (row) {
        $scope.form = angular.copy(row);
        if ($scope.selectedRow === row) {
            $scope.selectedRow = null;
        } else {
            $scope.selectedRow = row;
        }
    };
    //End

    //Hiển Thị page, phân trang
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
    //End

    //Khởi Tạo
    $scope.init = () => {
        var url = `${host}/products`;
        var productImagesUrl = `${host}/product-images`;
        Promise.all([$http.get(url), $http.get(productImagesUrl)])
            .then(([productsResp, imagesResp]) => {
                $scope.products = productsResp.data;
                $scope.productImages = imagesResp.data;

                $scope.productWithImage = $scope.products.map(product => {
                    const images = $scope.productImages
                        .filter(image => image.product.productId === product.productId)
                        .map(image => image.url);
                    return {
                        ...product,
                        images
                    };
                });
                $scope.totalItems = $scope.productWithImage.length;
                $scope.pageChanged();
                console.log("Success", $scope.page);
            }).catch((error) => {
                console.log("Error", error);
            });

        $http.get(`${host}/categories`).then((resp) => {
            $scope.selectBoxCates = resp.data;
        });
        $http.get(`${host}/suppliers`).then((resp) => {
            $scope.selectBoxSuppliers = resp.data;
        })
    };

    $scope.init();
});