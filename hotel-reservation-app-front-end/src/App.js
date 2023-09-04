import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./components/Home";
import About from "./components/About";
import Register from "./components/Register";
import Login from "./components/Login";
import AddHotelForm from "./Admin/AddHotelForm";
import Footer from "./components/Footer";
import BookingForm from "./components/BookingForm";
import MyBookings from "./User/MyBookings";
import UserProfile from "./User/UserProfile";
import User from "./User/User";
import Hotels from "./components/Hotels";
import GetAllRooms from "./Rooms/GetAllRooms";
import AdminHome from "./Admin/AdminHome";
import HotelListAdmin from "./Admin/HotelListAdmin.js"
import GetRooms from "./Admin/GetRooms";
import DeleteParticularRoom from "./Admin/DeleteParticularRoom";
import AddRoomForm from "./Admin/AddRoomForm";
import DeleteHotel from "./Admin/DeleteHotel";
import EditHotel from "./Admin/EditHotel";
import Rooms from "./Rooms/Rooms.js";
import Payment from "./Registration/Payment";
import FilterHotels from "./Hotels/FilterHotels";
import DataNotFound from "./ErrorPages/DataNotFound";


function App() {
  return (
    <>
      <Router>
        <Navbar />
        <Switch>
          <Route exact path="/">
            <Home />
          </Route>
          <Route exact path="/hotels">
            <Hotels />
          </Route>
          <Route exact path="/getAllrooms">
            <GetAllRooms />
          </Route>
          <Route exact path="/about">
            <About />
          </Route>
          <Route exact path="/register">
            <Register />
          </Route>
          <Route exact path="/login">
            <Login />
          </Route>
          <Route exact path="/booking">
            <BookingForm />
          </Route>
          <Route exact path="/User">
            <User />
          </Route>
          <Route exact path="/mybookings">
            <MyBookings />
          </Route>
          <Route exact path="/UserProfile">
            <UserProfile />
          </Route>

          <Route exact path="/admin">
            <AdminHome />
          </Route>
          <Route exact path="/hotel-list-admin">
            <HotelListAdmin />
          </Route>
          <Route exact path="/add-hotel">
            <AddHotelForm />
          </Route>
          <Route exact path="/get-room-list/:hotelId">
            <GetRooms />
          </Route>
          <Route exact path="/delete-room/:roomId">
            <DeleteParticularRoom />
          </Route>

          <Route exact path="/add-rooms/:hotelId">
            <AddRoomForm />
          </Route>
          <Route exact path="/delete-hotel/:hotelId">
            <DeleteHotel />
          </Route>
          <Route exact path="/edit-hotel/:hotelId">
            <EditHotel />
          </Route>


          <Route exact path="/userroom/:hotelId">
            <Rooms />
          </Route>
          <Route exact path="/payment"  >
            <Payment />
          </Route>

          <Route exact path="/filter-hotel/:selectedCity/:hotelType">
            <FilterHotels />
          </Route>
          <Route exact path="/data-not-found">
            <DataNotFound />
          </Route>




        </Switch>
        <Footer />
      </Router>

    </>
  );

}

export default App
