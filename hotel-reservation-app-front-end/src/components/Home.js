import React from "react"
import LandingPage from "./LandingPage";

import HotelFacilities from "../HomePages.js/HotelFacilities";
import HotelList from "../Hotels/HotelList.js";



const Home = () => {

    return (
        <div>
          <LandingPage/>
          <HotelList/>
          <HotelFacilities/>
  
      </div>
  );
}

export default Home;
