package main.java.controller;

import java.io.IOException;

import javax.swing.SwingUtilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import main.java.model.User;
import main.java.repository.UserRepository;
import main.java.view.AddUserView;

public class UserManageController extends BaseController {

    @FXML
    private TableView<User> userTable; // Bảng chứa danh sách người dùng

    @FXML
    private TableColumn<User, String> usernameColumn; // Cột username

    @FXML
    private TableColumn<User, String> gmailColumn; // Cột email

    @FXML
    private TableColumn<User, String> createdAtColumn; // Cột ngày tạo

    @FXML
    private TextField searchField; // Ô tìm kiếm

    @FXML
    private HBox paginationButtons; // HBox chứa các nút phân trang
    
    private UserRepository userRepository = new UserRepository();
    private ObservableList<User> userList = FXCollections.observableArrayList();
    private FilteredList<User> filteredList; // Danh sách được lọc
    
    private ObservableList<User> currentPageList = FXCollections.observableArrayList(); // Danh sách hiển thị ở trang hiện tại

    private int currentPage = 1; // Trang hiện tại
    private int rowsPerPage = 10; // Số hàng trên mỗi trang
    
    @FXML
    void TemplateManageClick(MouseEvent event) throws IOException {
    	switchToTemplateManage(event);
    }

    @FXML
    void adHomepageClick(MouseEvent event) throws IOException {
    	switchToAdminHomePage(event);
    }

    @FXML
    void userManageClick(MouseEvent event) throws IOException {
    	switchToUserManage(event);
    }
    
    @FXML
    public void initialize() {
        // Thiết lập cột
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        gmailColumn.setCellValueFactory(new PropertyValueFactory<>("gmail"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createAt"));

        // Tải dữ liệu từ repository
        loadUsers();

        // Thiết lập chức năng tìm kiếm
        setupSearch();

        // Hiển thị dữ liệu phân trang
        setupPagination();
    }

    private void loadUsers() {
        userList.clear();
        userList.addAll(userRepository.getAllUsers());

        // Khởi tạo FilteredList từ userList
        filteredList = new FilteredList<>(userList, b -> true);

        // Hiển thị dữ liệu trang đầu tiên
        setupPagination();
    }

    private void setupSearch() {
        // Lắng nghe sự thay đổi trong searchField
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Thiết lập điều kiện lọc cho filteredList
            filteredList.setPredicate(user -> {
                // Nếu ô tìm kiếm trống, hiển thị tất cả user
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Chuyển input thành chữ thường để so sánh không phân biệt hoa/thường
                String lowerCaseFilter = newValue.toLowerCase();

                // Lọc theo username hoặc email
                return user.getUsername().toLowerCase().contains(lowerCaseFilter)
                        || user.getGmail().toLowerCase().contains(lowerCaseFilter);
            });

            currentPage = 1;
            setupPagination();
        });
    }

    private void setupPagination() {
        // Tính toán số lượng trang
        int totalRows = filteredList.size();
        int totalPages = (int) Math.ceil((double) totalRows / rowsPerPage);

        if (currentPage > totalPages) {
            currentPage = totalPages;
        }
        if (currentPage < 1) {
            currentPage = 1;
        }

        // Cập nhật danh sách hiển thị cho trang hiện tại
        int fromIndex = (currentPage - 1) * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, totalRows);

        currentPageList.setAll(filteredList.subList(fromIndex, toIndex));
        userTable.setItems(currentPageList);
    }

    @FXML
    void goToFirstPage(ActionEvent event) {
        currentPage = 1;
        setupPagination();
    }
    
    @FXML
    void goToSecondPage(ActionEvent event) {
        currentPage = 2;
        setupPagination();
    }
    
    @FXML
    void goToThirdPage(ActionEvent event) {
        currentPage = 3;
        setupPagination();
    }

    @FXML
    void goToPreviousPage(ActionEvent event) {
        if (currentPage > 1) {
            currentPage--;
            setupPagination();
        }
    }

    @FXML
    void goToNextPage(ActionEvent event) {
        int totalPages = (int) Math.ceil((double) filteredList.size() / rowsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            setupPagination();
        }
    }

    @FXML
    void goToLastPage(ActionEvent event) {
        currentPage = (int) Math.ceil((double) filteredList.size() / rowsPerPage);
        setupPagination();
    }
    
    @FXML
    void addUserEnter(ActionEvent event) {
        SwingUtilities.invokeLater(() -> {
            AddUserView addUserView = new AddUserView();
            new AddUserController(addUserView, userRepository);

            // Thêm listener để kiểm tra khi cửa sổ đóng
            addUserView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    // Tải lại danh sách người dùng sau khi đóng cửa sổ
                    userList.clear();
                    userList.addAll(userRepository.getAllUsers());
                    setupPagination(); // Cập nhật lại bảng
                }
            });

            addUserView.setVisible(true);
        });
    }
    
    @FXML
    void deleteUserEnter(ActionEvent event) {
        deleteUser();
        setupPagination();
        
    }

    private void deleteUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userRepository.deleteUser(selectedUser.getUsername());
            userList.remove(selectedUser);         
        }
    } 
}
