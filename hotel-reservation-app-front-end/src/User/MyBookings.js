import React, { useState, useEffect } from "react";
import './MyBookings.css';
import axios from "axios";
import { Row, Col, Table, Button, Container } from "react-bootstrap";
import { useHistory } from "react-router-dom/cjs/react-router-dom";
import "animate.css";
import "bootstrap/dist/css/bootstrap.min.css";

const MyBookings = () => {
  const headers = {
    Authorization: localStorage.getItem('token').trim()
  };
  const [refresh, setRefresh] = useState(false);


  const [bookingList, setbookingList] = useState([]);

  useEffect(() => {
    // Make an Axios GET request to fetch hotel data from an API
    axios
      .get(`http://localhost:8080/booking/get-booking/${parseInt(localStorage.getItem('userId'))}`, { headers })
      .then((response) => {
        setbookingList(response.data);

        console.log("this is response  data ", response.data);
      })
      .catch((error) => {
        console.error("Error fetching hotel data:", error);
      });
  }, [bookingList]);

  const history = useHistory();



  // console.log("this is room Id "+ item[0].room.id);
  const navigateToCancleBooking = async (e) => {
    try {
      e.preventDefault();
      // Make the payment Axios request

      const paymentResponse = await axios.delete(`http://localhost:8080/booking/delete-booking/guest/${parseInt(localStorage.getItem('userId'))}/room/${bookingList[0].room.id}`, { headers }
      );
      alert("Your Booking get cancle successfully ")
      setRefresh(true);
      history.push('/mybookings');

    } catch (paymentError) {
      console.error('Payment error:', paymentError);
      // Handle payment error
    }


  };
  console.log(" this is  bookingList ", bookingList);

  return (
    <div className="my-bookings-container">
      <h2>My Bookings</h2>
      <table className="bookings-table">
        <thead>
          <tr>
            <th>
              <b>Booking Id </b>
            </th>
            <th>
              <b>Room Type </b>
            </th>
            <th>
              <b>Hotel Name </b>
            </th>
            <th>
              <b>Hotel Address </b>
            </th>
            <th>
              <b>Check-In-Date </b>
            </th>
            <th>
              <b>Check-Out-Date</b>
            </th>
            <th>
              <b>Amount:</b>
            </th>
            <th>
              <b>Cancel Booking:</b>
            </th>
          </tr>
        </thead>
        <tbody>
          {bookingList.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.room.roomType}</td>
              <td>{item.room.hotel.hotelName}</td>
              <td ><p> {item.hotel.address.streetAddress}</p>
                <p>{item.hotel.address.city}</p>
                <p>{item.hotel.address.state}</p>
                <p>{item.hotel.address.zipCode}</p>
              </td>


              <td>{item.bookingDate}</td>
              <td>{item.checkOutDate}</td>
              <td>{item.room.roomPrice}</td>

              <td>
                <Row>
                  <Col>
                    <Button
                      variant="danger"
                      className="mb-2 buttonSpacing animate__animated animate__fadeInLeft"
                      onClick={navigateToCancleBooking}
                    >
                      Cancel Booking
                    </Button>
                  </Col>

                </Row>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default MyBookings;
