const BASE_URL = "http://localhost:8080/api/v1";

/*
 * @param {String} username
 * @param {String} password
 * @returns
 */


const login = async (username, password) => {
    try {
        const res = await fetch(BASE_URL + "/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "username": username,
                "password": password
            }
        });
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        console.log(res.json());
        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}


/**
 * Gets all the general schedules for the crew
 * @returns {Promise<Array>}
 * @throws {Error}
 */
const viewGeneralSchedule = async () => {
    try {
        const res = await fetch(BASE_URL + "/gameSchedule");
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}

/**
 * Gets all the games in the schedule
 * @returns {Promise<Array>}
 * @throws {Error}
 */

const viewAllGamesInSchedule = async () => {
    try {
        const res = await fetch(BASE_URL + "/gameSchedule/games");
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }
        return res.json();
    }   catch (error) {
        console.log(error);
        throw error;
    }
}

/**
 * get a game by id
 * @param {Number} gameId
 * @returns {Promise<Object>}
 * @throws {Error}
 */
const viewGameById = async (gameId) => {
    try {
        const res = await fetch(BASE_URL + `/gameSchedule/game/${gameId}`);
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }
        return res.json();
    }   catch (error) {
        console.log(error);
        throw error;
    }
}


/**
 * Gets the crew list for a specific game
 * @param {Number} gameId
 * @returns {Promise<Object>}
 * @throws {Error}
 */
const viewCrewList = async (gameId) => {
    try {
        const res = await fetch(BASE_URL + `/crewList/${gameId}`);
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }
        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}


/**
 * 
 * @param {Number} id 
 * @returns 
 */ 
const crewProfile = async (id) => {
    try {
        const res = await fetch(BASE_URL + `/crewMember/${id}`);
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }
        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}


/**
 * 
 * @param {Number} id 
 * @param {*} crew 
 * @returns 
 */

const editCrewProfile = async (id, crew) => {
    try {
        const url = BASE_URL + `/crewMember/${id}`;
        const res = await fetch(url, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(crew),
        })
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}


/**
 * Creates a new crew profile
 * @param {*} crew 
 * @returns 
 */

const createCrewProfile = async (crew) => {
    try {
        const url = BASE_URL + "/crewMember";
        const res = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(crew),
        })
        if (!res.ok) {
            throw new Error(`Error: ${res.status}`)
        }

        return res.json();
    } catch (error) {
        console.log(error);
        throw error;
    }
}


export default {
    login,
    viewGeneralSchedule,
    viewCrewList,
    crewProfile,
    editCrewProfile,
    createCrewProfile,
    viewGameById,
    viewAllGamesInSchedule
}