document.addEventListener('DOMContentLoaded', () => {
    // --- Lấy các phần tử DOM cần thiết ---
    const movieSelect = document.getElementById('movieSelect');
    const cinemaSelect = document.getElementById('cinemaSelect');
    const dateSelect = document.getElementById('dateSelect');
    const timeButtons = document.querySelectorAll('#timeSlots .btn');
    const seatsGrid = document.getElementById('seatsGrid'); // Container chứa tất cả các ghế
    const allSeats = seatsGrid.querySelectorAll('.seat:not(.row-label):not(.booked)'); // Chỉ lấy ghế trống
    const checkoutBtn = document.getElementById('checkoutBtn');

    // --- Phần tử tóm tắt đơn hàng ---
    const summaryMovie = document.getElementById('summaryMovie');
    const summaryCinema = document.getElementById('summaryCinema');
    const summaryDate = document.getElementById('summaryDate');
    const summaryTime = document.getElementById('summaryTime');
    const summarySeats = document.getElementById('summarySeats');
    const summaryTotal = document.getElementById('summaryTotal');

    // --- Biến trạng thái ---
    let selectedSeats = []; // Mảng chứa ID của các ghế đang được chọn
    const baseTicketPrice = 100000; // Giá vé cơ bản, bạn có thể lấy từ API sau này

    // --- Hàm cập nhật tóm tắt đặt vé ---
    function updateSummary() {
        // Cập nhật thông tin phim, rạp, ngày, giờ
        summaryMovie.textContent = movieSelect.value || 'Chưa chọn';
        summaryCinema.textContent = cinemaSelect.value || 'Chưa chọn';
        summaryDate.textContent = dateSelect.value || 'Chưa chọn';

        let selectedTime = '';
        timeButtons.forEach(button => {
            if (button.classList.contains('active')) {
                selectedTime = button.dataset.time;
            }
        });
        summaryTime.textContent = selectedTime || 'Chưa chọn';

        // Sắp xếp các ghế đã chọn theo thứ tự A1, A2, B1, B2...
        selectedSeats.sort((a, b) => {
            const rowA = a.charCodeAt(0);
            const rowB = b.charCodeAt(0);
            const numA = parseInt(a.slice(1)); // Lấy số ghế
            const numB = parseInt(b.slice(1));

            if (rowA !== rowB) {
                return rowA - rowB;
            }
            return numA - numB;
        });

        // Cập nhật ghế đã chọn và tổng tiền
        summarySeats.textContent = selectedSeats.length > 0 ? selectedSeats.join(', ') : 'Chưa chọn';
        const total = selectedSeats.length * baseTicketPrice;
        summaryTotal.textContent = total.toLocaleString('vi-VN') + ' VND'; // Định dạng số tiền

        // Kích hoạt/Vô hiệu hóa nút thanh toán
        checkoutBtn.disabled = selectedSeats.length === 0 || !movieSelect.value || !cinemaSelect.value || !dateSelect.value || !selectedTime;
    }

    // --- Xử lý sự kiện chọn phim, rạp, ngày ---
    movieSelect.addEventListener('change', updateSummary);
    cinemaSelect.addEventListener('change', updateSummary);
    dateSelect.addEventListener('change', updateSummary);

    // --- Xử lý sự kiện chọn giờ chiếu ---
    timeButtons.forEach(button => {
        button.addEventListener('click', () => {
            // Bỏ active của tất cả các nút giờ chiếu khác
            timeButtons.forEach(btn => btn.classList.remove('active'));
            // Thêm active cho nút vừa click
            button.classList.add('active');
            updateSummary();
        });
    });

    // --- Xử lý sự kiện chọn ghế ---
    allSeats.forEach(seat => {
        seat.addEventListener('click', () => {
            const seatId = seat.dataset.seat; // Lấy ID ghế từ thuộc tính data-seat

            // Kiểm tra trạng thái ghế
            if (seat.classList.contains('booked')) {
                // Ghế đã đặt, không làm gì cả
                return;
            }

            if (seat.classList.contains('selected')) {
                // Ghế đang chọn, bỏ chọn
                seat.classList.remove('selected');
                selectedSeats = selectedSeats.filter(id => id !== seatId);
            } else {
                // Ghế trống, chọn ghế
                seat.classList.add('selected');
                selectedSeats.push(seatId);
            }
            updateSummary(); // Cập nhật lại tóm tắt sau khi chọn/bỏ chọn ghế
        });
    });

    // --- Xử lý sự kiện nút "Tiến hành thanh toán" ---
    checkoutBtn.addEventListener('click', () => {
        if (selectedSeats.length > 0) {
            alert(`Bạn đã chọn phim: ${summaryMovie.textContent}, rạp: ${summaryCinema.textContent}, ngày: ${summaryDate.textContent}, giờ: ${summaryTime.textContent}. \nSố ghế: ${selectedSeats.join(', ')}. \nTổng tiền: ${summaryTotal.textContent}. \nChức năng thanh toán sẽ được tích hợp tại đây.`);
            // Ở đây, bạn sẽ thực hiện chuyển hướng tới trang thanh toán
            // và gửi dữ liệu đặt vé lên Backend (qua API)
            // Ví dụ: window.location.href = `payment.html?movie=${movieSelect.value}&cinema=${cinemaSelect.value}&date=${dateSelect.value}&time=${summaryTime.textContent}&seats=${selectedSeats.join(',')}`;
        } else {
            alert('Vui lòng chọn ít nhất một ghế để tiếp tục.');
        }
    });

    // --- Khởi tạo trạng thái ban đầu khi tải trang ---
    updateSummary();
});