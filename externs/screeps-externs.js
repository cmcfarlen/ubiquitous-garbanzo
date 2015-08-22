var Game = {}

Game.getObjectById = function (id) {};
Game.getUsedCpu = function () {};

Game.spawns = {}
Game.spawns.createCreep = function (body, name, memory) {};

Game.creeps = {}
Game.creeps.move = function (direction) {};
Game.creeps.moveTo = function (x, y, opts) {};
Game.creeps.moveTo = function (target, opts) {};
Game.creeps.transferEnergy = function (target, amt) {};
Game.creeps.upgradeController = function (target) {};
Game.creeps.claimController = function (target) {};
Game.creeps.harvest = function (target) {};
Game.creeps.build = function (target) {};
Game.creeps.name;
Game.creeps.body;
Game.creeps.id;
Game.creeps.pos;
Game.creeps.carry;
Game.creeps.energy;
Game.creeps.memory;

Game.rooms = {}
Game.rooms.name;
Game.rooms.mode;
Game.rooms.controller;
Game.rooms.energyAvailable;
Game.rooms.energyCapacityAvailable;
Game.rooms.findPath = function (from, to, opts) {};
Game.rooms.find = function (type, opts) {};
Game.rooms.lookAt = function (x, y) {};
Game.rooms.lookAt = function (t) {};
Game.rooms.lookForAt = function (t, x, y) {};
Game.rooms.lookForAt = function (t, p) {};
Game.rooms.createConstructionSite = function (x, y, t) {};
Game.rooms.createConstructionSite = function (p, t) {};

Game.flags = {}

Game.cpuLimit;

Game.map;
Game.time;

Game.structures = {};

var Structure = {}
Structure.my;
Structure.hits;
Structure.maxHits;
Structure.id;
Structure.owner;
Structure.pos;
Structure.room;
Structure.structureType;
Structure.destory = function () {};

Structure.energy;
Structure.energyCapacity;
Structure.transferEnergy = function (tgt, amt) {};

Structure.cooldown;

Structure.level;
Structure.progress;
Structure.progressTotal;
Structure.ticksToDowngrade;

Structure.ticksToDegrade;
Structure.ticksToLive;
Structure.store;
Structure.storeCapacity;

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

var Source = {};
Source.pos



