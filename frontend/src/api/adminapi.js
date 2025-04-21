const BASE_URL = "http://localhost:3000/";

const crewList = async ()=>{
    const url = `${BASE_URL}/crewList`;

    try {
        const res = await fetch(url);

        if(!res.ok){
            throw new Error(`Error: ${res.status}`)
        }

        const data = await res.json();
        console.log(data);
        return data;
    } catch (error) {
        console.log(error);
    }
}


const gameSchedule = async (scheduleId) =>{
    const url = `${BASE_URL}/schedules/${scheduleId}/games`;
}