import React, { useState } from "react";
import { Link } from "react-router-dom";

import "./Navbar.css";

const Navbar = () => {

    const [authenticated, setAuthenticated] = useState(
        localStorage.getItem("authenticated") === "true"
    );

    const handleLogout = () => {
        localStorage.setItem("authenticated", "false");
        localStorage.setItem("token", '');
        localStorage.setItem("userId", '');
        alert("your are logOut Succesfully");
        window.location.href = '/';
        setAuthenticated(false);
    };


    return (
        <div>
            <nav className="navbar navbar-expand-lg border-bottom border-black text-blue py-3">
                <div className="container-fluid">
                    <Link className="navbar-brand" to="/">PLAN MY STAY</Link>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav mx-auto">
                            <li className="nav-item">
                                <Link className="nav-link active" aria-current="page" to="/">Home</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/hotels">Hotels</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/getAllrooms">Rooms</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/about">About</Link>
                            </li>

                            {/* <li className="nav-item">
                                <Link className="nav-link" to="/bookingform">BookingForm</Link>
                            </li> */}
                            {/* <li className="nav-item">
                                <Link className="nav-link" to="/UserPage">UserPage</Link>
                            </li> */}
                            {authenticated ? (
                            <li className="nav-item">
                                <Link className="nav-link" to="/User">User</Link>
                            </li>):( true)}
                            </ul>
                            <ul className="navbar-nav">
                            {authenticated ? (
                                <>
                                    <li className="nav-item">
                                        <button className="nav-link btn btn-link" onClick={handleLogout}>LogOut</button>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/mybookings">
                                            <button className="nav-link btn btn-link" >YourBooking</button>
                                        </Link>
                                    </li>

                                </>

                            ) :
                                (
                                    <>

                                      
                                            <li className="nav-item">
                                                <Link className="nav-link btn btn-sm" to="/register">Register</Link>
                                            </li>
                                            <li className="nav-item">
                                                <Link className="nav-link btn btn-sm" to="/login">Login</Link>
                                            </li>
                                        
                                    </>

                                )
                            }
                            </ul>




                    </div>
                </div>
            </nav>
        </div>
    );
}

export default Navbar;