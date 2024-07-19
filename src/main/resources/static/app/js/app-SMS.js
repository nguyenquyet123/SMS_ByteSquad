var app = angular.module("app-SMS", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "/app/spa/home.html"
        })
        .when("/thietLapChiNhanh", {
            templateUrl: "/app/spa/thietLapChiNhanh.html"
        })
        .when("/quanLyChiNhanh", {
            templateUrl: "/app/spa/quanLyChiNhanh.html"
        })
        .when("/quanLyNguoiDung", {
            templateUrl: "/app/spa/quanLyNguoiDung.html"
        })
        .when("/thietLapNguoiDung", {
            templateUrl: "/app/spa/thietLapNguoiDung.html"
        })
        .when("/thietLapVaiTro", {
            templateUrl: "/app/spa/thietLapVaiTro.html"
        })
        .when("/danhmuc", {
            templateUrl: "/app/spa/danhmuc.html"
        })
        .when("/themMoiHangHoa", {
            templateUrl: "/app/spa/themMoiHangHoa.html"
        })
        .when("/loaiSanPham", {
            templateUrl: "/app/spa/loaiSanPham.html",
            controller: "loaiSanPhamCtrl",
        })
        .when("/kiemkho", {
            templateUrl: "/app/spa/kiemkho.html"
        })
        .when("/themMoiKiemKho", {
            templateUrl: "/app/spa/themMoiKiemKho.html"
        })
        .when("/dathang", {
            templateUrl: "/app/spa/dathang.html"
        })
        .when("/nhaphang", {
            templateUrl: "/app/spa/nhaphang.html"
        })
        .when("/themMoiNhapHang", {
            templateUrl: "/app/spa/themMoiNhapHang.html"
        })
        .when("/chuyenhang", {
            templateUrl: "/app/spa/chuyenhang.html"
        })
        .when("/themMoiChuyenHang", {
            templateUrl: "/app/spa/themMoiChuyenHang.html"
        })
        .when("/hoadon", {
            templateUrl: "/app/spa/hoadon.html"
        })
        .when("/themMoiHoaDon", {
            templateUrl: "/app/spa/themMoiHoaDon.html"
        })
        .when("/khoHang", {
            templateUrl: "/app/spa/khoHang.html"
        })
        .when("/themMoiKhoHang", {
            templateUrl: "/app/spa/themMoiKhoHang.html"
        })
        .when("/khachhang", {
            templateUrl: "/app/spa/khachhang.html"
        })
        .when("/themMoiKhachHang", {
            templateUrl: "/app/spa/themMoiKhachHang.html"
        })
        .when("/nhacungcap", {
            templateUrl: "/app/spa/nhacungcap.html"
        })
        .when("/themMoiNhaCungCap", {
            templateUrl: "/app/spa/themMoiNhaCungCap.html"
        })
        .when("/bccuoingay", {
            templateUrl: "/app/spa/bccuoingay.html"
        })
        .when("/bcbanhang", {
            templateUrl: "/app/spa/bcbanhang.html"
        })
        .when("/bchanghoa", {
            templateUrl: "/app/spa/bchanghoa.html"
        })
        .when("/banHang", {
            templateUrl: "/app/spa/banHang.html",
            controller: "banHangCtrl",
        })
        .otherwise({
            redirectTo: "/"
        });

    // $httpProvider.defaults.headers.common["Authorization"] = "Basic c3RhZmY6MTIz";
})

let host = "http://localhost:8080/api";

app.controller("banHangCtrl", function ($scope, $http) {

    $scope.product = {};
    $scope.products = [];
    $scope.reset = () => {
        $scope.product = {};
    };

    $scope.currentPage = 1;
    $scope.itemsPerPage = 8;
    $scope.totalItems = 2;
    $scope.page = {};

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
        $scope.page = $scope.products.slice(begin, end);
    };

    $scope.load_all = () => {
        var url = `${host}/products`;
        $scope.product = {};
        $http
            .get(url)
            .then((resp) => {
                $scope.products = resp.data;
                $scope.totalItems = $scope.products.length;
                $scope.pageChanged();
                console.log("Success", resp);
            })
            .catch((error) => {
                console.log("Error", error);
            });
    };

    $scope.load_all();
    // Quan Ly Gio hang
    $scope.cart = {
        items:[],
        //Them san pham 
        add(id){
            var item = this.items.find(item => item.productId == id);
            if(item){
                item.qty++;
                this.saveToLocalStorage();
            }else{
                $http.get(`/api/products/${id}`).then(resp =>{
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
            this.showAlert('Thêm mới Thành Công');
        },
        showAlert(mess) {
            var alertBox = document.createElement('div');
            alertBox.innerHTML = mess;
            alertBox.style.cssText = 'position:fixed;top:9%;right:40%;width:20%;border-radius: 10px;background-color:rgba(100, 147, 255,1);color:white;text-align:center;padding:20px;z-index:9999;';
            document.body.appendChild(alertBox);
        
            setTimeout(function() {
                document.body.removeChild(alertBox);
            }, 1500); // Đóng alert sau 1 giây
        },
        remove(id){
            var index = this.items.findIndex(item => item.productId == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
            this.showAlert('Xóa Thành Công');
        },
        clear(){
            this.items = [];
            this.saveToLocalStorage();
        },
        get count(){
            return this.items
            .map(item => item.qty)
            .reduce((total,qty) => total += qty, 0);
        },
        get amount(){
            return this.items
            .map(item => item.qty * item.unitPrice)
            .reduce((total,qty) => total += qty, 0);
        },
        //Luu gio hang vao local storage
        saveToLocalStorage(){
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart",json);
        },
        //Doc gio hang tu local storage
        loadFromLocalStorage(){
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    }

    $scope.cart.loadFromLocalStorage();

    $scope.order ={
        createDate: new Date(),
        address:"",
        account:{username:$("#username").text().trim()},
        get orderDetails(){
            return $scope.cart.items.map(item =>{
                return {
                    product: {id:item.id},
                    price: item.price,
                    quantity: item.qty
                }
            })
        },
        purchase(){
            var order = angular.copy(this);
            $http.post("/rest/orders",order).then(resp => {
                alert("Đặt Hàng thành công");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.id;
            }).catch(error => {
                alert("Đặt Hàng thất bại");
                console.log(error);
            })
        }
    }
});
