import { Kweet } from "./Kweet"

export class User {
    id: string;
    username: string;
    firstname: string;
    lastname: string;
    biography: string;
    website: string;
    location: string;
    followers: Array<string>;
    following: Array<string>;
    roles: Array<string>;
    kweets: Array<Kweet>;

    constructor(obj: UserInterface = {} as User) {
        const {
            id = "",
            username = "",
            firstname = "",
            lastname = "",
            biography = "",
            website = "",
            location = "",
            followers = [],
            following = [],
            roles = [],
            kweets = []
        } = obj;

        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.biography = biography;
        this.website = website;
        this.location = location;
        this.followers = followers;
        this.following = following;
        this.roles = roles;
        this.kweets = kweets;
    }
}

export interface UserInterface {
    id: string;
    username: string;
    firstname: string;
    lastname: string;
    biography: string;
    website: string;
    location: string;
    followers: Array<string>;
    following: Array<string>;
    roles: Array<string>;
    kweets: Array<Kweet>;
}