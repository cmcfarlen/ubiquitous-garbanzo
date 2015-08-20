var Game = {}

Game.getObjectById = function (id) {};
Game.getUsedCpu = function () {};

Game.spawns = {}
Game.spawns.createCreep = function (body, name, memory) {};

Game.creeps = {}
Game.creeps.move = function (direction) {};
Game.creeps.moveTo = function (x, y, opts) {};
Game.creeps.moveTo = function (target, opts) {};
Game.creeps.name;
Game.creeps.body;
Game.creeps.id;
Game.creeps.pos;

Game.rooms = {}
Game.rooms.name;
Game.rooms.findPath = function (from, to, opts) {};

Game.flags = {}

Game.cpuLimit;

Game.map;
Game.time;

Game.structures = {};

var RoomPosition = function (x, y, room) {};

RoomPosition.roomName;
RoomPosition.x;
RoomPosition.y;
RoomPosition.inRangeTo = function (pos, range) {};
RoomPosition.inRangeTo = function (x, y, range) {};
RoomPosition.isNearTo = function (pos) {};
RoomPosition.isNearTo = function (x, y, pos) {};
RoomPosition.getRangeTo = function (x, y) {};
RoomPosition.getRangeTo = function (pos) {};

var Memory = {};


