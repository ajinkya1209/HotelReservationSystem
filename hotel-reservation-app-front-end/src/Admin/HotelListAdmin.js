import React, { useState, useEffect } from "react";
import axios from "axios";
import { Row, Col, Table, Button, Container } from "react-bootstrap";
import { useHistory } from "react-router-dom/cjs/react-router-dom";
import "animate.css";
import "bootstrap/dist/css/bootstrap.min.css";

const HotelListAdmin = () => {
  const [hotels, setHotels] = useState([]);

  useEffect(() => {
    
    axios
      .get("http://localhost:8080/hotel/get-hotel")
      .then((response) => {
        setHotels(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.error("Error fetching hotel data:", error);
      });
  }, []);

  const history = useHistory();
  const navigateToEdit = (hotel) => {
    history.push(`/edit-hotel/${hotel.hotelId}`, { hotelData: hotel });
  };

  const navigateToAddRooms = (hotel) => {
    history.push(`/add-rooms/${hotel.hotelId}`, { hotelData: hotel });
  };

  const navigateToDeleteHotel = (hotel) => {
    const confirmDelete=window.confirm(`Are you sure want to delete ${hotel.hotelName}`)
    if(confirmDelete)
      history.push(`/delete-hotel/${hotel.hotelId}`, { hotelData: hotel });
    else
    return;
  
  };

  const navigateToGetRooms = (hotel) => {
    console.log("before history.push");
    console.log(hotel);
    history.push(`/get-room-list/${hotel.hotelId}`, { hotelData: hotel });
  };

  const navigateToAddHotel = () => {
    history.push('/add-hotel',{ hotelData: hotels}); // Pass the hotel object as state
  };

  const navigateToLogout = (e) => {
    //localStorage.setItem('userId','');
    localStorage.setItem('authenticated',false);
    //console.log("handler");
    history.push('/admin',{});
    ///console.log("after history.push");
  }
  return (
    
    <div >
    <button className="btn btn-primary" onClick={ () => navigateToAddHotel(hotels)}>Add A New Hotel</button> 
    <button className="btn btn-danger float-end" onClick={()=>navigateToLogout()}>Logout</button>
    <br/><br/>
      <h2 className="animate__animated animate__fadeInDown">
        Existing Hotels List
      </h2>
      <br/>
      <Table
        striped
        bordered
        hover
        className="animate__animated animate__fadeIn"
      >
        <thead>
          <tr>
            <th>
              <b>Hotel ID</b>
            </th>
            <th>
              <b>Hotel Name</b>
            </th>
            <th>
              <b>Hotel Type</b>
            </th>
            <th>
              <b>Actions</b>
            </th>
          </tr>
        </thead>
        <tbody>
          {hotels.map((hotel) => (
            <tr key={hotel.hotelId}>
              <td>{hotel.hotelId}</td>
              <td>{hotel.hotelName}</td>
              <td>{hotel.hotelType}</td>
              <td>
                <Row>
                  <Col>
                    <Button
                      variant="primary"
                      className="mb-2 buttonSpacing animate__animated animate__fadeInLeft"
                      onClick={() => navigateToEdit(hotel)}
                    >
                      Edit Hotel
                    </Button>
                  </Col>
                  <Col>
                    <Button
                      variant="success"
                      className="mb-2 buttonSpacing animate__animated animate__fadeInLeft"
                      onClick={() => navigateToAddRooms(hotel)}
                    >
                      Add Room
                    </Button>
                  </Col>
                  <Col>
                    <Button
                      variant="danger"
                      className="mb-2 buttonSpacing animate__animated animate__fadeInLeft"
                      onClick={() => navigateToDeleteHotel(hotel)}
                    >
                      Delete Hotel
                    </Button>
                  </Col>
                  <Col>
                    <Button
                      variant="danger"
                      className="mb-2 buttonSpacing animate__animated animate__fadeInLeft"
                      onClick={() => navigateToGetRooms(hotel)}
                    >
                      Delete Room
                    </Button>
                  </Col>
                </Row>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default HotelListAdmin;
