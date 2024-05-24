import React, { useState } from "react";
import KorMap from "./KorMap";
import PopulationTable from "./PopulationTable";
import "../Css/Page.css";
import styled from "styled-components";

const selectBox = styled.div`
  float: right;
`;
function LocalStatistical() {
  const [selectedJob, setSelectedJob] = useState("");

  const handleJobChange = (event) => {
    setSelectedJob(event.target.value);
  };

  return (
    <div>
      <selectBox>
        <h2>직업별 검색</h2>
        <select value={selectedJob} onChange={handleJobChange}>
          <option value="">직업을 선택하세요</option>
          <option value="engineer">Engineer</option>
          <option value="teacher">Teacher</option>
          <option value="doctor">Doctor</option>
          <option value="designer">Designer</option>
        </select>
      </selectBox>
      <div className="container">
        <KorMap />
        <PopulationTable />
      </div>
    </div>
  );
}

export default LocalStatistical;
