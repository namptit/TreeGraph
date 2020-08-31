# TreeGraph
Represent a tree json as a graph

Input: Tree json like this below

```json
[
    {
      "id": 52,
      "name": "BU-FRANCE",
      "parent": 964,
      "children": [58]
    },
    {
      "id": 53,
      "name": "BU-GERMANY",
      "parent": 964,
      "children": [61, 62, 63]
    },
    {
      "id": 58,
      "name": "BU-QUAL",
      "parent": 52,
      "children": [59, 60]
    },
    {
      "id": 59,
      "name": "BU-BUSINESS",
      "parent": 58,
      "children": []
    },
    {
      "id": 60,
      "name": "BU-HR",
      "parent": 58,
      "children": []
    },
    {
      "id": 61,
      "name": "BU-QUAL1",
      "parent": 53,
      "children": []
    },
    {
      "id": 62,
      "name": "BU-BUSINESS1",
      "parent": 53,
      "children": []
    },
    {
      "id": 63,
      "name": "BU-HR1",
      "parent": 53,
      "children": []
    },
    {
      "id": 964,
      "name": "CUSTOMER GROUP",
      "parent": null,
      "children": [52,53]
    }
]
```
And the definition of Node:

``` text
interface Node {
    id: number;
    name: string;
    children: number[];
    parent: number | null;
  }

type NodeArray = Node[];
```
Output: 
Represent Tree json as a graph like:

``` text
CUSTOMER GROUP
├─ BU-FRANCE
|  └─ BU-QUAL
|     ├─ BU-BUSINESS
|     └─ BU-HR
└─ BU-GERMANY
   ├─ BU-QUAL1
   ├─ BU-BUSINESS1
   └─ BU-HR1
```
   
##################################################

