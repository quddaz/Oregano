import React from 'react';
import './PopulationTable.css';

const data = [
    { region: '서울', population: '4,616,624', growth: '0.8%' },
    { region: '부산', population: '779,568', growth: '1.5%' },
    { region: '대구', population: '511,389', growth: '2.5%' },
    { region: '인천', population: '683,536', growth: '2.3%' },
    { region: '광주', population: '317,475', growth: '1.1%' },
    { region: '대전', population: '424,453', growth: '1.4%' },
    { region: '울산', population: '330,798', growth: '2.2%' },
    { region: '세종', population: '81,430', growth: '4.7%' },
    { region: '경기', population: '3,610,187', growth: '2.3%' },
    { region: '충북', population: '449,168', growth: '2.5%' },
    { region: '충남', population: '605,754', growth: '3.5%' },
    { region: '전남', population: '447,947', growth: '2.7%' },
    { region: '경북', population: '684,795', growth: '1.7%' },
    { region: '경남', population: '851,962', growth: '2.8%' },
    { region: '제주', population: '159,531', growth: '1.9%' },
    { region: '전북', population: '416,147', growth: '2.6%' },
    { region: '강원', population: '359,788', growth: '2.1%' },
    { region: '전국', population: '81,430', growth: '1.8%' } // 전국 증감률 데이터 추가
  ];

  
function PopulationTable(){
  return (
    <div className="table-container">
      <table className="population-table">
        <thead>
          <tr>
            <th>지역(명)</th>
            <th>인구</th>
            <th>전년동월대비 증감률</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={index}>
              <td>{item.region}</td>
              <td>{item.population}</td>
              <td>{item.growth}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PopulationTable;
