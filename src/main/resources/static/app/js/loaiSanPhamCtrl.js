app.controller('loaiSanPhamCtrl', function($scope) {
    $scope.switchTab = function(event, tabId) {
        event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ <a>
        
        // Xóa lớp 'active' từ tất cả các tab
        var tabs = document.querySelectorAll('.nav-link');
        tabs.forEach(function(tab) {
            tab.classList.remove('active');
        });

        // Thêm lớp 'active' vào tab được nhấp
        var clickedTab = document.getElementById(tabId + '-tab');
        clickedTab.classList.add('active');

        // Ẩn tất cả các nội dung tab
        var tabContents = document.querySelectorAll('.tab-pane');
        tabContents.forEach(function(content) {
            content.classList.remove('show', 'active');
        });

        // Hiển thị nội dung của tab được nhấp
        var activeContent = document.getElementById(tabId);
        activeContent.classList.add('show', 'active');
    };
});