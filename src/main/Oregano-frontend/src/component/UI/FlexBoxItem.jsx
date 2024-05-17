import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 20px;
  background-color: #f0f0f0;
`;

const Card = styled.div`
  flex: 1 1 30%;
  margin: 0 10px;
  padding: 20px;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: left;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Title = styled.h3`
  margin: 0 0 10px;
  font-size: 18px;
  color: #333;
  text-align: left;
`;

const Number = styled.p`
  margin: 0;
  font-size: 24px;
  font-weight: bold;
  color: #000;
`;

const Percentage = styled.p`
  margin: 10px 0 0;
  font-size: 22px;
  color: ${props => (props.isPositive ? 'blue' : 'red')};
`;

function StatisticCard({ title, number, percentage, isPositive }) {
  return (
    <Card>
      <Title>{title}</Title>
      <hr></hr>
      <Number>{number}</Number>
      <Percentage isPositive={isPositive}>{percentage}</Percentage>
    </Card>
  );
}

function FlexBoxItem(props) {
  return (
    <Container>
      {props.statistics.map((stat, index) => (
        <StatisticCard 
          key={index}
          title={stat.title} 
          number={stat.number} 
          percentage={stat.percentage} 
          isPositive={stat.isPositive} 
        />
      ))}
    </Container>
  );
}

export default FlexBoxItem;
