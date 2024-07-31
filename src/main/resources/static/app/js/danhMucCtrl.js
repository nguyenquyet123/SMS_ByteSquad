app.controller("danhMucCtrl", function ($scope, $http) {
    $scope.form = {};
    $scope.items = [];//dùng để lưu tạm sau khi load
    //sử dụng để hiển thị trang và tìm kiếm
    $scope.products = [];
    $scope.username = document.getElementById('username').textContent;

    //Khởi Tạo
    $scope.init = () => {
        //load Chi nhánh
        $http.get(`${host}/branches`).then((resp) => {
            $scope.selectBoxBranchs = resp.data;
        }).catch(error => {
            console.log("Error: ", error.message)
        });
        console.log("select", $scope.selectedOption);
        var url = `${host}/products`;
        if ($scope.selectedOption) {
            url = `${host}/products/${$scope.selectedOption.branchId}/branch`;
        }
        var productImagesUrl = `${host}/product-images`;
        $http.get(url).then(respProd =>{
            $scope.products = respProd.data;
            $http.get(productImagesUrl).then(respProdImg =>{
                $scope.productImages = respProdImg.data;

                //Lưu danh sách sau khi load
                $scope.items = $scope.products.map(product => {
                    const images = $scope.productImages
                        .filter(image => image.product.productId === product.productId)
                        .map(image => image.url);
                    return {
                        ...product,
                        images
                    };
                });
                $scope.filteredItems = $scope.items;
            }).catch(error =>{
                console.log("ErrorProImg: ",error)
            });
        }).catch(error =>{
            console.log("ErrorPro: ",error)
        });
        //load categories
        $http.get(`${host}/categories`).then((resp) => {
            $scope.selectBoxCates = resp.data;
        });
        //load Nhà Cung Cấp
        $http.get(`${host}/suppliers`).then((resp) => {
            $scope.selectBoxSuppliers = resp.data;
        })
    };

    $scope.init();

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
            console.log("SaveProImg: ", reps.data);
        }).catch(error => {
            console.log(error);
        })
    }
    //Create
    $scope.create = function () {
        var item = angular.copy($scope.form);
        item.productStatus = 1;
        item.giaNhap = 0;
        console.log("create: ",item)
        $http.post(`${host}/products`, item).then(reps => {
            $scope.product = reps.data;
            console.log("CreatePro", reps.data);
            $scope.saveProImg();
            alert("Them moi thanh cong");
            $scope.reset();
        }).catch(error => {
            alert("Them moi that bai");
            console.log("Error", error.message);
        })
    }
    //Update
    $scope.update = function () {
        var item = angular.copy($scope.form);
        console.log(item)
        $scope.saveProImg();
        $http.put(`${host}/products/${item.productId}`, item).then(reps => {
            var index = $scope.items.findIndex(p => p.productId == item.productId);
            $scope.items[index] = item;

            alert("Cap nhat thanh cong");
            console.log("Update : ", $scope.items[index]);
        }).catch(error => {
            alert("Cap nhat that bai");
            console.log("Error", error);
        });
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

    //Hiển Thị page, phân trang , Tìm kiếm 
    $scope.searchKeyword = '';
    //filteredItems sử dụng để tìm kiếm,đổ tất cả lên trang hoặc theo key search
    console.log("b", $scope.items)

    $scope.filterItems = function () {
        console.log("a", $scope.filteredItems)
        console.log("b", $scope.items)
        $scope.filteredItems = $scope.items.filter(function (item) {
            //Các trường của list số + .toString(),chữ + toLowerCase()
            return item.productId.toString().includes($scope.searchKeyword) ||
                item.productName.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                item.unitPrice.toString().includes($scope.searchKeyword) ||
                item.quantity.toString().includes($scope.searchKeyword) ||
                item.category.name.toLowerCase().includes($scope.searchKeyword.toLowerCase()) ||
                item.supplier.companyName.toLowerCase().includes($scope.searchKeyword.toLowerCase());
        });
        $scope.pager.first();
    };

    $scope.pager = {
        page: 0,
        size: 8,
        // dùng để duyệt mảng và đổ lên client
        get items() {
            var start = this.page * this.size;
            return $scope.filteredItems.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.filteredItems.length / this.size);
        },
        first() {
            this.page = 0;
        },
        pre() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }
        },
        last() {
            this.page = this.count - 1;
        }
    };
    //End
});