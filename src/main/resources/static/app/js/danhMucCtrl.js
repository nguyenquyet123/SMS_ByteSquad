app.controller("danhMucCtrl", function ($scope, $http) {
    $scope.form = {};
    $scope.products = [];

    //reset
    $scope.reset = () => {
        $scope.form = {};
    }
    //Upload Hinh
    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);

        $http.post('/rest/upload/img', data, {
            transformStream: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(function (res) {
            if ($scope.form.images != null) {
                $scope.form.images.push(res.data.name.trim());
            } else {
                $scope.form.images = res.data.name.trim();
            }
            alert("Chon Anh Thanh Cong");
        }).catch(function (error) {
            console.log("Error", error);
        });
    };
    $scope.saveProImg = function () {
        var img;
        var product;
        if (Array.isArray($scope.form.images)) {
            img = $scope.form.images.at(-1);
            product = $scope.form;
        } else {
            img = $scope.form.images;
            product = $scope.product;
        };
        
        $scope.proImg = {
            url: img,
            product: product
        };
        $http.post(`${host}/product-images`, $scope.proImg).then(reps => {
            console.log("SaveProImg: ",reps.data);
        }).catch(error => {
            console.log(error);
        })
    }
    //Create
    $scope.create = function () {
        var item = angular.copy($scope.form);
        item.productStatus = 1;
        $http.post(`${host}/products`, item).then(reps => {
            $scope.product = reps.data;
            console.log("CreatePro",reps.data);
            $scope.saveProImg();
            alert("Them moi thanh cong");
            $scope.reset();
        }).catch(error => {
            alert("Them moi that bai");
            console.log("Error", error);
        })
    }
    //Update
    $scope.update = function () {
        var item = angular.copy($scope.form);
        console.log(item)
        $scope.saveProImg();
        $http.put(`${host}/products/${item.productId}`, item).then(reps => {
            var index = $scope.page.findIndex(p => p.productId == item.productId);
            $scope.page[index] = item;
            alert("Cap nhat thanh cong");
            console.log("Update : ",reps.data)
        }).catch(error => {
            alert("Cap nhat that bai");
            console.log("Error", error);
        });
    }

    //Hiển thị table
    $scope.selectedRow = null;
    $scope.showDetails = function (row) {
        console.log(row)
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
        //load categories
        $http.get(`${host}/categories`).then((resp) => {
            $scope.selectBoxCates = resp.data;
        });
        //load categories
        $http.get(`${host}/suppliers`).then((resp) => {
            $scope.selectBoxSuppliers = resp.data;
        })
    };

    $scope.init();
});